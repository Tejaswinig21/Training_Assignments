import java.util.*;
/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;*/
public class Account {
    static HashMap<Integer,List> map=new HashMap<Integer,List>();
    static int Account_number=1000;
    String Account_holder;
    int balance=0;
    static int ndeposits=0;
    static int nwithdraws=0;

    public int create(String name)
    {
        Account_number+=1;
        Account_holder=name;
        map.put(Account_number,new ArrayList<>());
        map.get(Account_number).add(Account_holder);
        map.get(Account_number).add(balance);
        map.get(Account_number).add(ndeposits);
        map.get(Account_number).add(nwithdraws);
        return Account_number;
    }
    public int deposit(int account_number,int amount){

        if((int) map.get(account_number).get(1)+amount<100000 && amount>=500 && amount<=50000 && (int) map.get(account_number).get(2)<3)
        {
            map.get(account_number).set(1,(int) map.get(account_number).get(1)+amount);
            map.get(account_number).set(2,(int) map.get(account_number).get(2)+1);
            return (int) map.get(account_number).get(1);
        }
        else if(amount<500)
        {
            System.out.println("Minimum deposit amount is 500");
        }
        else if(amount>50000)
        {
            System.out.println("Maximum deposit amount is 50000");
        }
        else if((int) map.get(account_number).get(2) >= 3){
            System.out.println("Only 3 deposits are allowed in a day");
        }
        return 0;
    }
    public int withdraw(int acc_num,int amount){
        if((int) map.get(acc_num).get(1)-amount>=0 && amount>=1000 && amount<=25000 && (int) map.get(acc_num).get(3)<3){
            map.get(acc_num).set(1,(int) map.get(acc_num).get(1)-amount);
            map.get(acc_num).set(3,(int) map.get(acc_num).get(3)+1);
            return (int) map.get(acc_num).get(1);
        }
        else if(amount<1000){
            System.out.println("Minimum withdraw amount is 1000");
        }
        else if(amount>25000){
            System.out.println("Maximum withdraw amount is 25000");
        }
        else if((int) map.get(acc_num).get(3) >= 3){
            System.out.println("Only 3 withdrawals are allowed in a day");
        }
        else if(amount>(int) map.get(acc_num).get(1)){
            System.out.println("Insufficient balance");
        }
        return 0;
    }
    public int getBalance(int account_number){
        return (int) map.get(account_number).get(1);
    }
    public void transfer(int src,int des,int amt){
        if(amt<1000){
            System.out.println("Minimum withdrawal amount is 1000 for "+src);
        }
        else if(amt>25000){
            System.out.println("Maximum withdrawal amount is 25000 for"+src);
        }
        else if(amt<500)
        {
            System.out.println("Minimum deposit amount is 500 for "+des);
        }
        else if(amt>50000)
        {
            System.out.println("Maximum deposit amount is 50000 for "+des);
        }
        else if((int) map.get(des).get(2) >= 3){
            System.out.println("Only 3 deposits are allowed in a day for "+des);
        }
        else if((int) map.get(src).get(3) >= 3){
            System.out.println("Only 3 withdrawals are allowed in a day for "+src);
        }
        else{
            withdraw(src,amt);
            deposit(des,amt);
            System.out.println("Successful");
        }
    }
   /* public static void main(String args[]){
        Account ac1=new Account();
        Account ac2=new Account();
        Account ac3=new Account();
        ac1.create("Tejaswini");
        ac2.create("Rajesh");
        ac3.create("Mahesh");
        ac1.deposit(10000);
        System.out.println(ac2.Account_number+" "+ac2.Account_holder+" "+ ac2.balance);

    }*/

    /*public static void main(String[] args)
    {
        HashMap<Integer,Account> acc=new HashMap<Integer,Account>();
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split(" ");
        switch (s[0]){
            case "create":
                Account ac=new Account();
                ac.create(s[1]);
                break;
            case "deposit":
                System.out.println(s[1]+" "+s[2]);
                break;
            case "withdraw":
                break;
            case "transfer":
                break;
            default:
                System.out.println("nothing");
        }*/


    }

