package model;
import javax.persistence.*;
@Entity
@Table(name = "pseudoQueueModel")
public class PseudoQueueModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name ="accountNumber")
    private String accountNumber;
    @Column(name = "type")
    private String type;
    @Column(name = "amount")
    private String amount;
    @Column(name = "currency")
    private String currency;
    @Column(name = "accountFrom")
    private String accountFrom;

    //default constructor
    public PseudoQueueModel(){

    }
    public PseudoQueueModel(String accountNumber, String type, String amount, String currency, String accountFrom){
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.accountFrom = accountFrom;
    }
    public long getId(){
        return id;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getAmount(){
        return amount;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }
    public String getCurrency(){
        return currency;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getAccountFrom(){
        return accountFrom;
    }
    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }


}
