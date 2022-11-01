import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Anwarul Patwary 
 * @version 1.00, 01 April 2022
 */
public class Order
{
    // instance variables 
    private FileReader file;
    private ArrayList<Product> product_list; 
    private String orderID;
    private double total_price;
    private double total_discount; 
    private String invoice_reference;
    private int total_item;

    /**
     * 1) Constructor for objects of class Order
     * 2) Initialise instance variables
     * 3) Provide the input file name and first letter of your first name and last as an order ID
     * e.g ("input.txt" "CP") CP for Collin Peter
     * 4) Get the list of lines from fileReader class. Create anonymous Product type of objects, then add them into product_list. 
     */
    public Order(String fileName, String order_id)
    {
        //TO DO 1
        file = new FileReader(fileName);
        file.getLines();
        product_list = new ArrayList<Product>();
        for (String s : file.getLines()) {
            product_list.add(new Product(s));
        }
        orderID = fileName + " " + order_id;
        total_price = 0.0;
        total_discount = 0.0;
        total_item = 0;
        invoice_reference = "unknown";
    }

    /**
     * Generate invoice ID using OrderID and random generated value. String concatenation is applicable here. 
     * if orderID = "AN", and random value is 18. 
     * example output: AN18
     */

    public String generateInvoice(){
       //TO DO 8
       String[] i_id = orderID.split(" ");
       int upperbound = 101;
       java.util.Random r = new Random();
       int random_value= r.nextInt(upperbound) + 1;
       String n = String.valueOf(random_value);
       invoice_reference = i_id[1] + n;
        return invoice_reference;
    }

    /**
     * 1) This method must communicate with Product class
     * 2) The method should be able to place the order using product id and unit. It will take one product item at a time. 
     * 3) Calculate the total payable amount by the customer
     * 4) To place multiple orders method should be able to get called multiple times. The total price and total discount will be accumulated to the field variable total_price total_discount respectively.
     * 5) Return the accumulated total price from this method
     * @param e.g prod_id = "P10982" and unit = 2
     * 6) Return the value with two decimal places. For Example: from 19.887 to 19.89
     */
    public double placeOrder(String prod_id, int unit){
    //TO DO 9
    double price = 0.0;
    double discounted_price = 0.0;
    double discount = 0.0;
    for(Product p : product_list) { 
        if (p.getProductID().equals(prod_id))
        price = p.getPrice();
    }
    for(Product p : product_list) { 
        if (p.getProductID().equals(prod_id))
        discount = (100-(p.getDiscount()))/100;
    }
    discounted_price = ((price * discount) * unit);
    total_price = total_price + discounted_price;
    total_discount = total_discount + ((price * unit) - discounted_price);// original price minus the discounted price.
    total_price = Math.round(total_price*100.0)/100.0;
    total_discount = Math.round(total_discount*100.0)/100.0;
    total_item = total_item + unit;
    return total_price;
    }
    
    /**
     * 1) This method must communicate with Product class
     * 2) Return the accumulated total price after placing the order. 
     * 3) Return the value with two decimal places. For Example: from 19.887 to 19.89
     */
    public double getTotalPrice() { 
        //TO DO 10
        return total_price;
    }

    /**
     * 1) This method must communicate with Product class
     * 2) Get the accumulated total amount of discount that has been applied after placing the order.
     * 3) Return the value with two decimal places. For Example: from 1.113 to 1.11
     */

    public double getTotalDiscount(){
        //TO DO 11
        return total_discount;
    }

    /**
     * Return total number of items ordered by a customer
     */
    public int getTotalItem(){
        //TO DO 12
        return total_item;
    }

    /**
     * print orders in the follwing format. 
     * *******Order Details*******
     * Invoice Number: AN43
     * Total items :2
     * Amount Payable: 36.7
     * Discount Applied: 2.4
     */

    public void printOrder(){
       //TO DO 13
        System.out.println("*******Order Details*******");
        System.out.println("Invoice Number:" + invoice_reference);
        System.out.println("Total item:" + total_item);
        System.out.println("Amount_payable:" + total_price);
        System.out.println("Discount Applied:" + total_discount);
    } 
  
}
