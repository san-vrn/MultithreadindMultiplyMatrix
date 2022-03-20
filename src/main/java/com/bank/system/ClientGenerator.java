package com.bank.system;

import java.util.Random;

public class ClientGenerator extends Thread {
    private Random random;
    //среднее время обслуживания
    private final int SERVICE_TIME = 30000;
    //среднее количество клиентов в минуту
    private final int CLIENTS_PER_MINUTE = 10;
    //минимальная сумма операции
    private final int minAmount = 100;
    //максимальная сумма операции
    private final int maxAmount = 5000;
    //банк
    private Bank bank;

    public ClientGenerator(Bank bank) {
        this.random = new Random();
        this.bank = bank;
    }

    @Override
    public void run() {
        while (true){

        }
    }

    public BankClient createClient() throws InterruptedException {
        if(this.getState() == State.RUNNABLE){
            BankClient client = new BankClient();
            client.setTimeOfService((int)(random.nextInt(SERVICE_TIME)*Math.random())+1);
            client.setTypeOperation(generateTypeOperation());
            client.setTransactionAmount(random.nextInt(maxAmount - minAmount)+minAmount);
            System.out.println("пришел новый клиент, операция: " + client.getTypeOperation()+ "время выполнения операции" + client.getTimeOfService());
            return client;
        }
        else {
            return null;
        }

    }

    public void pauseThread(){
        try {
            Thread.sleep((long) (60000/CLIENTS_PER_MINUTE * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public TypeOperation generateTypeOperation() {
        if (random.nextInt(2)==1) {
            return TypeOperation.depositMoney;
        } else {
            return TypeOperation.withdrawMoney;
        }
    }

    public void sleeping(int time){
        try {
            this.sleep(time);
            System.out.println(this.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
