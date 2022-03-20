package com.bank.system;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Bank {
    private static Bank bank;
    //счет в банке
    private BankCashVault bankCashVault;
    //операционисты
    private List<OperationMan> operationManList;
    //клиент
    private BankClient bankClient;
    //генератор клиентов
    private ClientGenerator clientGenerator;
    //количество операционистов
    private final int operationManCount = 5;
    //рандомайзер
    private Random random;
    //log
    private Logger logger;
    //флаг цикла while
    private boolean flag;

    public Bank() {
        this.random = new Random();
        this.operationManList = new ArrayList<>(operationManCount);
        this.bankCashVault = new BankCashVault(random.nextInt(50000)+5000);
        this.logger = Logger.getLogger("Bank logger");
        this.flag = true;
        this.clientGenerator = new ClientGenerator(this);
        this.clientGenerator.start();
        createOperationMan(operationManCount);
    }

    public BankCashVault getBankCashVault() {
        return bankCashVault;
    }

    public void start() throws InterruptedException {
        while (flag) {
                bankClient = null;
                bankClient = clientGenerator.createClient();
                if (bankClient != null){
                    clientGenerator.pauseThread();
                    operationManList.sort(Comparator.comparingInt(OperationMan::getSizeQueue));
                    operationManList.get(0).addClient(bankClient);
                }
                for(OperationMan operationMan : operationManList){
                    System.out.println( "у операциониста № " + operationMan.getName() + " в очереди " +  operationMan.getSizeQueue() + " клиентов");
                }
            }

    }

    private void createOperationMan(int operationManCount){
        for (int i = 0; i<operationManCount; i++){
            operationManList.add(new OperationMan(this));
            operationManList.get(i).start();
            System.out.println(operationManList.get(i).getName() + " работает");
        }
    }


}
