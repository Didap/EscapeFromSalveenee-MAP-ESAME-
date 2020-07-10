
/*
lo scopo del gioco è prendere la nave per scappare, quindi dopo aver preso tutti
gli oggetti di gioco ed aver interagito con ogni elemento (inclusa la ragazza
imprigionata) potrete scegliere se fuggire con lei o meno, dandole il medikit
e facendovi seguire. L'unico nemico che può farvi "terminare" il gioco si trova
al piano di sopra.

 */


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author w
 */
public class EscapeFromSalveenee{

    //
    
    private static Scanner lukija = new Scanner(System.in);
    private static String ttyConfig;
    static boolean stop = false;
    static boolean typed = false;
  
    public static void main(String[] args) {
        
        
    Grid upperDeck = new Grid();
    Grid lowerDeck = new Grid();
    char[][] upperDeckChar, lowerDeckChar;
    int floor=0;
    Player player = new Player(9,38);
    
    UserInput userinput=new UserInput();
    ArrayList<Item> reppu;
    String line = "";
    init(userinput,upperDeck,lowerDeck);
    
    
    // intro del game
    AsciiPics.title();
    System.out.println("Premi un tasto per continuare");
    line = lukija.nextLine();
    AsciiPics.startInfo(userinput);
    System.out.println("Premi un tasto per continuare");
    line = lukija.nextLine();
    // acquisisci il tipo di OS
    String os = System.getProperty("os.name");
       //
    //System.out.println(os);
    
    if(os.toUpperCase().contains("WINDOWS")){
          
        Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException ex) {
                Logger.getLogger(EscapeFromSalveenee.class.getName()).log(Level.SEVERE, null, ex);
            }
            //
            while( stop == false ){
                
                printCharShip(userinput);
                printInfo(userinput);
                
                line = lukija.nextLine();
                if(line.equalsIgnoreCase("q")){
                    System.out.println("Non me ne andrei se fossi in te...");
                    stop = true;
                }
                robot.delay(300);
                robot.keyPress(KeyEvent.VK_ENTER);
                typed(line, userinput);
                
            }   
    } 
    
    // OS is linux or Mac os
    else{
   
                  // ***
         // linux raw console
         try {
                    RawConsole.setTerminalToCBreak();

                    while ( stop == false) {
                            
			    printCharShip(userinput);
                            printInfo(userinput);
                            //System.out.print("Give your command(\"Esc\" to quit): ");
                        
                                    try {
                                    Thread.sleep(300);
                                    } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                    }
                                    if ( System.in.available() != 0 ) {
                                    int c = System.in.read();
                                    if ( c == 0x1B ) {
                                        System.out.println("Non me ne andrei se fossi in te...");    
                                        stop = true;
                                            
                                    }
                                    // a or A
                                    else if( c == 0x61){
                                        line = "A";
                                        typed(line, userinput);
                                    }
                                    // d or D
                                    else if(c == 0x64){
                                        line = "D";
                                        typed(line, userinput);
                                    }
                                    else if(c == 0x77){
                                        line = "W";
                                        typed(line, userinput); 
                                    }
                                    else if(c == 0x73){
                                        line = "S";
                                        typed(line, userinput);
                                    }
                                    else if(c == 0x75){
                                       line = "U";
                                       typed(line, userinput); 
                                    }
                                    else if(c == 0x74){
                                       line = "T";
                                       typed(line, userinput); 
                                    }
                                    else if(c == 0x67){
                                       line = "G";
                                       typed(line, userinput); 
                                    }
                                    else{
                                       stop = false;
                                    }              
                            }
                    } // end while
                }
                catch (IOException e) {
                        System.err.println("Eccezione");
                }
                catch (InterruptedException e) {
                        System.err.println("Interruzione");
                }
                finally {
                    try {
                        RawConsole.stty( ttyConfig.trim() );
                     }
                     catch (Exception e) {
                         System.err.println("Attenzione, chiudere il terminale");
                     }
                }
            }
                
    }
    
    
    public static void init(UserInput userinput, Grid upperDeck, Grid lowerDeck){
            
    HashMap<String, String> lowerdeckItems = new HashMap<String, String>();
    HashMap<String, String> upperdeckItems = new HashMap<String, String>(); 
    HashMap<String,String> lowerdeckUseSquares = new HashMap<String, String>();
    HashMap<String,String> upperdeckUseSquares = new HashMap<String, String>();

    // game data    
    lowerdeckItems.put("0","Medikit"); 
    lowerdeckItems.put("1","Chiave");
    lowerdeckItems.put("2","Card della sicurezza");

    upperdeckItems.put("0","Formaggio stagionato");
    upperdeckItems.put("1","Chiave");

    lowerdeckUseSquares.put("0","Escape pod:null:EXIT:EXIT");
    lowerdeckUseSquares.put("1",":Formaggio stagionato:GET:RaspberryPie");
    //ottenendo il raspberry pie potrete hackerare il central computer e scappare
    upperdeckUseSquares.put("0","Central computer:RaspberryPie:OPEN:@");
    upperdeckUseSquares.put("1","Security computer:Card della sicurezza:OPEN:&");

    
                char[][] lowerdeck = new char[][]{
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',' ',' ',' ','#',' ','#',' ',' ',' ','#','#','#',' ',' ',' '},
                new char[] {' ',' ',' ','#','#','#','#','#','1','1','1','*','1','#',' ','#','#','#',' ','#','#','#',' ',' ',' ',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},
                new char[] {' ',' ','#','#','2','2','2','#','#','.','#','#','#','#',' ','#','O','#',' ','#','#','#',' ',' ','#','5','5','5','5','5','5','5','5','5','|','6','6','6','6','6','#','#',' ',' ',' '},
                new char[] {' ','#','#','2','2','2','2','2','2','2','#','#','#','#','#','#','@','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','6','*','6','6','#','#','#','#','#','#'},
                new char[] {'#','#','2','2','2','2','2','2','2','2','.','.','.','.','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','4','#','#','#','#','#','#','#','#','#','#','#'}, 
                new char[] {' ','#','#','2','2','2','2','2','2','2','#','#','L','L','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','-','#','6','6','A','6','#','#','#','#','#','#'},
                new char[] {' ',' ','#','#','2','2','2','#','#','.','#','#','#','#',' ','#','#','#',' ','#','#','#',' ',' ','#','5','5','O','5','5','5','5','5','5','&','6','6','6','6','6','#','#',' ',' ',' '},
                new char[] {' ',' ',' ','#','#','#','#','#','3','3','3','3','*','#',' ','#','#','#',' ','#','#','#',' ',' ',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},        
                new char[] {' ',' ',' ',' ',' ',' ','#','#','#','#','#','#','#','#',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','#','#','#',' ',' ',' ','#',' ','#',' ',' ',' ','#','#','#',' ',' ',' '},
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                  
               };

               //stampa delle sale;

                char[][] upperdeck = new char[][]{
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},        
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},    
                new char[] {' ',' ',' ',' ',' ','#','#','#',' ',' ',' ',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},
                new char[] {' ',' ',' ','#','#','G','#','#','#','#','#','#','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ',' ',' ','#','y','.','y','y','y','y','y','#','#',' ',' ',' '},
                new char[] {' ',' ','#','7','#','7','#','7','7','7','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','y','y','*','y','#','#','#','#','#','#'},
                new char[] {'#','#','O','7','7','7','7','7','7','7','.','.','.','.','.','.','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','#','#','#','#','#','#','#','#','#','#','#'},
                new char[] {'#','#','7','7','7','7','7','7','7','7','#','#','L','L','#','#','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','8','.','g','g','g','g','#','#','#','#','#','#'},
                new char[] {' ',' ','#','7','#','7','#','7','7','7','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','g','g','g','#','#','#','#','#','#'},
                new char[] {' ',' ',' ','#','#','7','#','#','#','#','#','#','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ',' ',' ','#','*','g','g','g','g','g','O','#','#',' ',' ',' '},
                new char[] {' ',' ',' ',' ',' ','#','#','#',' ',' ',' ',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#',' ','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#',' ',' ',' '},
		new char[] {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}       
              };

                 //  printCharShip(upperdeck);
                  upperDeck.initGrid(upperdeck,upperdeckItems,upperdeckUseSquares); 
                  lowerDeck.initGrid(lowerdeck,lowerdeckItems,lowerdeckUseSquares); 
                  
                  userinput.setDecks(lowerDeck, upperDeck);
                 // printCharShip(userinput.getCurrentMap());
                  
                  
    }

    /**
     * 
     * @param table 
     */
    public static void printCharShip(UserInput userinput){
         
        char[][] table = userinput.getCurrentMap();
        
        if(userinput.floor() == 0){ 
            for(int i=0;i<table.length;i++){
            for(int j=0;j<table[0].length;j++){
               if(Player.x == i && Player.y == j ){
                    System.out.print("W");
                } 
               else if((NpcWoman.x == i) && (NpcWoman.y == j)){
                    System.out.print("A");
                   }  
               else{
                System.out.print(table[i][j]);                
               }
                //System.out.print("["+i+j+"]"+lowerdeck[i][j]);
            }
            System.out.println("");
        }
        }
        else{
            //attiva i movimenti della guardia
            if(NpcGuard.move()){
            
                for(int i=0;i<table.length;i++){
                    for(int j=0;j<table[0].length;j++){
                       if(Player.x == i && Player.y == j ){
                            System.out.print("W");
                        } 
                       else if((NpcGuard.x == i) && (NpcGuard.y == j)){
                            System.out.print("G");
                           } 
                       else{
                        System.out.print(table[i][j]);                
                       }
                        //System.out.print("["+i+j+"]"+lowerdeck[i][j]);
                    }
                System.out.println("");
                }
            }
        }
 
    }
    

    
    public static void printInfo(UserInput userinput){
       if(stop == false){ 
    
        String[] itemsInBag = new String[5];
        for(int z = 0;z < 5 ; z++ ){
            itemsInBag[z] = "";
        }        
        if(userinput.getItems().size()>0){
        for(int i = 0; i< userinput.getItems().size();i++){
             itemsInBag[i] ="["+i+"]: "+userinput.getItems().get(i).name(); 
        }
        }
        
        System.out.println(" _________________________________________________________________");
        System.out.println("|"+userinput.getMessage());
        System.out.println("|__________________________|___________________________ZAINO______|");
        System.out.println("| w = su                   | g = prendi oggetto |"+itemsInBag[0]);
        System.out.println("| s = giu                  | u = usa oggetto    |"+itemsInBag[1]);
        System.out.println("| a = sinistra             | t = parla          |"+itemsInBag[2]);
        System.out.println("| d = destra               |                    |"+itemsInBag[3]);
        System.out.println("Inserisci il comando + il tasto ENTER");
        System.out.print("NotaBene: puoi inserire più di un comando alla volta es: 'aaaaa' son 5 passi a sinistra");
        System.out.println("Comando:");
        
       } 
        
        
    }

   

    
    public static void typed(String jono, UserInput userinput) {
           
              
                        if( jono.trim().toUpperCase().equals("A")){
                           userinput.gameLogic('a');
                        }
                        if( jono.trim().toUpperCase().equals("D")){
                            userinput.gameLogic('d');
                        }
                        if( jono.trim().toUpperCase().equals("W")){     
                            userinput.gameLogic('w');
                        }
                        if( jono.trim().toUpperCase().equals("S")){
                            userinput.gameLogic('s');
                        }
                        if(jono.trim().toUpperCase().equals("U")){
                            userinput.gameLogic('u');
                        }
                        if(jono.trim().toUpperCase().equals("T")){
                            userinput.gameLogic('t');
                        }
                        if(jono.trim().toUpperCase().equals("G")){
                            userinput.gameLogic('g');
                        }
    }


    
}