
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**

 * 
 * 
 * @author w
 */
public class Bag {

    ArrayList<Item> stuff;
            
    public Bag(){  
      stuff = new ArrayList();     
    }
    
    public ArrayList getItems(){
        return stuff;
    }
    
    
    public boolean findItem(String name){
       boolean found = false;
       for(int i = 0; i< stuff.size();i++){
           if(stuff.get(i).name().contains(name)){
              found = true; 
              return found;
           }
       }
       return found;
    }
    
    
    public Item getItem(String name){
        Item item = new Item(0,0,"");
         for(int i = 0; i< stuff.size();i++){
           if(stuff.get(i).name().contains(name)){
              return stuff.get(i);
           }
       }
         return item;
    }
    
    
    public String addItem(String item){
        String  message;
        System.out.println(":"+item);
        stuff.add(new Item(0,0,item));
        
        if(item.trim().equals("Chiave")){
         message= " Ecco una chiave. Portala con te";
        }
        else if(item.equals("Hacking device")){
         message = " Attiri i topi dal loro nido con il formaggio. \\ N Vedi un oggetto nel nido e lo afferri."
                 + " È un dispositivo di hacking! Non lasciare che la paura blocchi il tuo percorso!";   
        }
        else{
            message="Hai preso: " + item;
        }
        return message;
    }
    
    /**
     * Käytetään itema ja poistetaan se repusta
     * @param item 
     */
    public void useItem(Item item){       
        stuff.remove(item);
    }
   
}
