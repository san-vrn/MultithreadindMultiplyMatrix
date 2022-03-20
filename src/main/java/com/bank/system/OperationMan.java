package com.bank.system;

import java.util.ArrayList;
import java.util.List;

public class OperationMan extends Thread{
    //очередь клиентов
    private List<BankClient> bankClientList;
    //доступ к счету банка
    private BankCashVault cashVault;

    Bank bank;


    public OperationMan(Bank bank) {
        this.bankClientList = new ArrayList<>();
        this.cashVault = bank.getBankCashVault();
        this.bank = bank;
    }

    @Override
    public void run() {
        while (true){
            if(!controlClientList()){
                try {
                    serveTheClients();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }
    }


    public void addClient(BankClient bankClient){
        bankClientList.add(bankClient);
        controlClientList();
    }

    public int getSizeQueue(){
        return bankClientList.size();
    }

    private synchronized boolean controlClientList(){
        if(bankClientList.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }
        else {
            this.notify();
            return false;
        }
    }

    public void serveTheClients() throws InterruptedException{
        synchronized (cashVault){
            //если очередь не пустая
            if(!bankClientList.isEmpty()){
                System.out.println("денег перед операцией" + cashVault.getCash());
                //обслуживаем первого клиента в очереди
                BankClient client = bankClientList.get(0);
                //если операция - снятие наличных
                if(client.getTypeOperation()==TypeOperation.withdrawMoney){
                    //если у нас в кассе наличных хватает для выдачи, то выдаем, если не хвататет, то отправляем клиента в конец очереди ждать наличных
                    if((cashVault.getCash()- client.getTransactionAmount()) >=0){
                        cashVault.withdrawMoney(client.getTransactionAmount());
                        System.out.println("деньги сняты"+ cashVault.getCash() );
                        this.sleep(client.getTimeOfService());
                        bankClientList.remove(client);
                    }
                    else {
                        bankClientList.remove(client);
                        bankClientList.add(client);
                    }
                }
                //если операция - внесение наличных
                else {
                    cashVault.depositMoney(client.getTransactionAmount());
                    System.out.println("деньги внесены"+ cashVault.getCash() );
                    this.sleep(client.getTimeOfService());
                    bankClientList.remove(client);
                }
            }
        }
    }

}
