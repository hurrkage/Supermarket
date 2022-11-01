import java.util.ArrayList;

/**
 * @author Anwarul Patwary 
 * @version 1.00, 01 April 2022
 */
public class Store
{
    // instance variables 
    private FileReader file;
    private ArrayList<Product> listOfproduct;

    /**
     * 1) Constructor for objects of class Store
     * 2) Initialise instance variables
     * 2) Get the list of line from fileReader class. Create anonymous Product type of objects, then add them into listOfproduct.
     */
    
    public Store(String fileName)
    {
       //TO DO 14
       file = new FileReader(fileName);
       file.getLines();
       listOfproduct = new ArrayList<Product>();
       for (String s2 : file.getLines()){
           listOfproduct.add(new Product(s2));
       }
    }

    /**
     * 1)Find the most expensive product of a category.
     * @param e.g. "veg"
     * 2)Return the product id of the product.
     */
    public String expensiveItem(String prod_category)
    {
        //TO DO 15
        ArrayList<Product> xs = new ArrayList<>();// if the product matches the product_category it is added onto a new ArrayList.
        for (Product p : listOfproduct) {
            if (p.getCategory().contains(prod_category))
            xs.add(p);
        }
        double z1 = xs.get(0).getPrice();
        double z2 = xs.get(1).getPrice();
        for (Product s : xs) {
            if (s.getPrice() > z1) {
            return s.getProductID();
            }
            else{
                if (s.getPrice() > z2) 
                return s.getProductID();
            }
        }
        return null;
    }

    /**
     * 1) Find the most expensive product of a category from a particular store.
     * 2) Return the product id and price of a product. Return the value in a single line as a string using string concatenation. 
     * Example output: P10982,21.0
     */

    public String expensiveItemStore(String prod_category, String store_id){
        //TO DO 16
        ArrayList<Product> ps = new ArrayList<>();// Adds all the products from the same store into a new ArrayList.
        for (Product p2 : listOfproduct) {
            if (p2.getStoreID().contains(store_id))
            ps.add(p2);
        }
        double n1 = ps.get(0).getPrice();
        double n2 = ps.get(1).getPrice();
        double n3 = ps.get(2).getPrice();
        for (Product s : ps) {
            if (s.getCategory().contains(prod_category)){
                if ((s.getPrice() > n2)) {
                return s.getProductID() + "," + s.getPrice();
                }
                else if (s.getPrice() > n3) {
                    return s.getProductID() + "," + s.getPrice();
                }
            }
        }
        return null;
    }

    /**
     * 1) Find the list of product and price which price is between "min" and "max". 
     * @param e.g min = 2, and max =10. 
     * 2) Return the list in an accumulated string using string concatenation.
     * 3) The list should be a String type, each product details seperate by a new line using "\n".  
     * 4) Throw an exception for a negative argument and "min" must not be greater than "max" value.
     * 
     * An example of returning list:
     * P10082,4.71
     * P10032,3.54
     */

    public String findProduct(int min, int max) {
        //TO DO 17
        if (min > max)
        throw new IllegalArgumentException("max should be greater than min");
        String z = "";
        for (Product p3 : listOfproduct) {
            if ( (min <= p3.getPrice()) && (p3.getPrice() <= max) )
            z += p3.getProductID() + "," + p3.getPrice() + "\n";
        }
        return z;
    } 

    /**
     * Use the prod_id to check the price of that product. Throw an exception if no product matches the product id.
     * @param e.g "P10982"
     */
    public double checkPrice(String prod_id){
        //TO DO 18
        ArrayList<String> product = new ArrayList<>();
        double n = 0.0;
        for (Product p23 : listOfproduct) {
            product.add(p23.getProductID());
        }
        
        if (!product.contains(prod_id))
        throw new IllegalArgumentException("no product matches the product id");
        
        for (Product p4 : listOfproduct) {
            if (p4.getProductID().equals(prod_id)) {
                n = p4.getPrice();
            }
        }
        return n;
    }

    /**
     * 1) Find the list of products in a store using a store ID. Return the product id and price using an ArrayList of String type
     * 2) Throw an exception if no store matches the store id.
     * @param e.g store_id = "S0198"
     * Example output: 
     * P10082,4.71
     * P10032,3.54
     */

    public ArrayList<String> getproductbyStoreID(String store_id){
        //TO DO 19
        ArrayList<String> stores = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        for (Product p23 : listOfproduct) {
            stores.add(p23.getStoreID());
        }
        
        if (!stores.contains(store_id))
        throw new IllegalArgumentException("no store matches the store id");
        
        for (Product p6 : listOfproduct) {
            if (p6.getStoreID().equals(store_id)) {
            s.add(p6.getProductID() + "," + p6.getPrice());
            }
        }
        return s;
    }

    /**
     * 1) Get the average cost of an item in an item category.
     * @param e.g "veg"
     * 2) Throw an exception in calculating the average that a value can not be divided by zero.
     * 3) Return the value with two decimal places. For Example: from 19.887 to 19.89
     */

    public double averageCost(String prod_category){
        //TO DO 20
        ArrayList<String> category = new ArrayList<>();
        double z = 0.0;
        double cat_size = 0;
        for (Product pff : listOfproduct) {
            if (pff.getCategory().equals(prod_category))
            cat_size += 1;
        }
        
        if (cat_size == 0)
        throw new ArithmeticException("value cannot be divided by zero");
        
        for (Product p5: listOfproduct) {
            if (p5.getCategory().contains(prod_category))
            z += p5.getPrice();
        }
        return Math.round((z/cat_size)*100.0)/100.0; 
    }

}
