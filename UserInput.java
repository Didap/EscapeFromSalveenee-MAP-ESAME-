
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author w
 * 
 * game state 
 * map char[][]
 * player x,y
 * 
 */
public class UserInput{
    
    
    static String message="Sei William, un prigioniero della nave Leghista \n "
            + "Scappa, non farti prendere!";
    int floor;
    Grid upperDeck; 
    Grid lowerDeck;
    char[][] currentMap;
    Grid currentGrid;
    int x,y;
    Bag reppu;
    
    
    
   public UserInput(){
      
   }
   
   public void setDecks(Grid lowerdeck, Grid upperdeck){
       
       upperDeck = upperdeck;
       lowerDeck = lowerdeck;
       floor = 0;
       setCurrentMap();
       reppu = new Bag();
   }
    
   public ArrayList<Item> getItems(){
       return reppu.getItems();
   }
  
   /**
    * message
    * @return 
    */
    public String getMessage(){
        return message;
    }
    
  
    public char[][] getCurrentMap(){
        return currentGrid.charGrid();
        
    }

    public int floor(){
        return floor;
    }
    
    
     
    // ci si muove con wasd
        // i muri sono #
       
    /**
     movimento del player sulla mappa fatta di caratteri
     
     */
    public void gameLogic(char c){
       
        if(c == 'a' || c == 'd' || c == 'w' || c == 's'){
            
            int npcx = Player.x;
            int npcy = Player.y;
            
        if(c == 'a'){    
            x = Player.x;
            y = Player.y-1;
            movePlayer(x,y);
        }

        if(c == 'd'){
            x = Player.x;
            y = Player.y+1;
            movePlayer(x,y);
        }

        if(c == 'w'){
            x = Player.x-1;
            y = Player.y; 
            movePlayer(x,y);
        }

        if(c == 's'){
            x = Player.x+1;
            y = Player.y;
            movePlayer(x,y);
        }
        
                    
          
            
    }

        // U = usa
     
        if(c == 'u'){
            if(currentGrid.charGrid()[Player.x][Player.y] == 'O'){ 
                message = useSquareUse(Player.x, Player.y);   
            }
            else{
                message = "Avrei bisogno di una chiave inglese per usarlo..." ;
            }
         } 



        // T = parla
       
        if(c == 't'){
             
             if(NpcWoman.x == Player.x && NpcWoman.y == Player.y ||
                NpcWoman.x == Player.x && NpcWoman.y == Player.y-1 ||
                NpcWoman.x == Player.x && NpcWoman.y == Player.y+1 ||
                NpcWoman.x == Player.x-1 && NpcWoman.y == Player.y ||
                NpcWoman.x == Player.x+1 && NpcWoman.y == Player.y){
                 if(floor == 0){
                     NpcWoman.talk(reppu); 
                 }
                 else{
                     message = "Mi piacerebbe restare e parlare ma, ... uhm ... devo andare ...";
                 }
                
             }
             else{
                 message = "Ok parlo..., gne gne gne";
             }
            
        }

        // G = prendi (da terra)
        
        if(c == 'g'){
           if(currentGrid.charGrid()[Player.x][Player.y] == '*'){
              message = reppu.addItem(getItem(Player.x,Player.y).name());
               currentGrid.setSquare(Player.x, Player.y, '.');   
           }
           else{
               message = "Non raccoglierlo";
           }
        }
        
        
    }
    
    public void setCurrentMap(){
         if(floor==0){
           currentMap = lowerDeck.charGrid();  
           currentGrid = lowerDeck;
           NpcGuard.setCoords(0,0);
       }
         else{
           currentMap = upperDeck.charGrid();  
           currentGrid = upperDeck;
           NpcGuard.reset();
         }
    }
    
    
    
    public void movePlayer(int mx, int my){
          
          if(currentGrid.charGrid()[mx][my] == '#'){
          }
          else if(currentGrid.charGrid()[mx][my] == '@'){
              message = "Una porta si è aperta da qualche parte";
          } 
          else if(currentGrid.charGrid()[mx][my] == '&'){
              message = "Una porta si è aperta da qualche parte";
          }
          
          // controlla chiave
          else if (currentGrid.charGrid()[mx][my] == '|' || currentGrid.charGrid()[mx][my] == '-'){
                
                if(reppu.findItem("Chiave")){
                    currentGrid.charGrid()[mx][my] = '.';
                    Item poistettava = reppu.getItem("Chiave");
                    reppu.useItem(poistettava);
                    message = "Hai preso una chiave, potrai aprire una porta.";
                }
                else{
                    message = "La porta è chiusa, probabilmente c'è una chiave da qualche parte\n"
                            + "dai un occhiata in giro per trovarla";
                }
          }
           
            
            
          else{
                    if(currentGrid.charGrid()[mx][my] == 'O'){
                        message = useSquareMessage(mx,my);
                    }
                    if(currentGrid.charGrid()[mx][my] == '*'){
                        message = itemMessage(mx,my);
                    }
                    if(currentGrid.charGrid()[mx][my] == '.'){
                        message = areaMessage(mx,my); 
                    }
                    if(currentGrid.charGrid()[mx][my] == 'L'){
                        if(floor == 0){
                            floor = 1;
                            setCurrentMap();
                        }
                        else{
                            floor = 0;
                            setCurrentMap();
                        }
                         message = areaMessage(mx,my); 
                    }
                if(NpcWoman.follow){
 
                   NpcWoman.setCoords(Player.x, Player.y);
                  
                }    
                  
                Player.y = my;
                Player.x = mx;
                
                if(Player.x == NpcGuard.x && Player.y == NpcGuard.y){
                    AsciiPics.prisoner();
                    
                    EscapeFromSalveenee.stop = true;
                }
                
            
            
            }
    }
    
    
    
    public String useSquareMessage(int mx, int my){
        UseSquare sq;
        String mes = "";
        for(int i = 0; i<currentGrid.uses().size(); i++){
                     sq = (UseSquare)currentGrid.uses().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 mes = sq.name();       
                            }
        }
        return mes;
    }
    
     public String useSquareUse(int mx, int my){
        UseSquare sq;
        String mes = "Non puoi farlo--Non ancora";
        for(int i = 0; i<currentGrid.uses().size(); i++){
                     sq = (UseSquare)currentGrid.uses().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 if(reppu.findItem(sq.neededItem().trim()) || sq.neededItem().contains("null")){
                                     
                                     //controlla piano

                                    mes = sq.command(sq.getCommand().trim(), lowerDeck, upperDeck, reppu); 
                              }        
                            }
        }
        return mes;
    }
    
   
     
     
    public String areaMessage(int mx, int my){
        Area sq;
        String mes = "";
        for(int i = 0; i<currentGrid.areas().size(); i++){
                     sq = (Area)currentGrid.areas().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 mes = sq.name();       
                            }                    
        }
        return mes;
    }
    
   
    
    
     public String itemMessage(int mx, int my){
        Item sq = new Item(0,0,"null");
        String mes = "";
        for(int i = 0; i<currentGrid.items().size(); i++){
                     sq = (Item)currentGrid.items().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                mes = sq.name();      
                            }                    
        }
        return mes;
    }
    
     
     
     
     
    public Item getItem(int mx, int my){
        Item sq = new Item(0,0,"null");
        for(int i = 0; i<currentGrid.items().size(); i++){
                sq = (Item)currentGrid.items().get(i);
                            if((sq.getX() == mx) && (sq.getY() == my)){
                                 return sq;      
                            }                    
        }
        return sq;
    }
    
    
}
