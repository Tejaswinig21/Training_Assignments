import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Transaction extends Account {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> account=new ArrayList<Account>();
        //HashMap<Integer, List> acc = new HashMap<Integer, List>();
        while(true)
        {
            String s[] = sc.nextLine().split(" ");
            Account ac = new Account();
            switch (s[0]) {
                case "create":
                    String st=String.join(" ",s);
                    System.out.println(ac.create(st.substring(7)));
                    account.add(ac);
                    System.out.println(map);
                    break;
                case "deposit":
                    int res=ac.deposit(Integer.parseInt(s[1]),Integer.parseInt(s[2]));
                    if(res!=0){
                        System.out.println(res);
                    }
                    System.out.println(map);
                    break;
                case "withdraw":
                    int re=ac.withdraw(Integer.parseInt(s[1]),Integer.parseInt(s[2]));
                    if(re!=0)
                    System.out.println(re);
                    System.out.println(map);
                    break;
                case "balance":
                    System.out.println(ac.getBalance(Integer.parseInt(s[1])));
                    break;
                case "transfer":
                    ac.transfer(Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]));
                    System.out.println(map);
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
        }
    }
}
