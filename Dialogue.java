

import java.util.Scanner;

/**
 *
 * @author w
 */
public class Dialogue {
   
    private static Scanner lukija = new Scanner(System.in);
    
    static int visited = 0;
    
    /**
     * 
     * @return 
     */
    public static void talkToGirl(boolean follow){
       
        // if you have aid
        if(follow){
            followDialogue(); 
        }
        else{
            basicDialogue();
        }
        visited ++;
        System.out.println("Premi un tasto per continuare");
        lukija.nextLine();
        
    }
    

    public static void basicDialogue(){
        
        //System.out.println(visited);
        AsciiPics.theGirl();
        
        if(visited == 0){
            System.out.println("Tu: Chi sei?...");
            lukija.nextLine();
            System.out.println("Donna prigioniera: Sono Aino, una prigioniera della colonia 51");
            System.out.println("la mia famiglia è stata sterminata, ed io ho sono stata catturata");
            System.out.println("hanno abusato di me, tuttavia, anche sul mio pianeta spesso accadeva...");
            System.out.println("da quando è iniziata la guerra non ho vissuto un solo giorno in pace");
            System.out.println("sei la prima persona con cui parlo da settimane...puoi aiutarmi?");
            lukija.nextLine();
            System.out.println("Tu: Vieni con me, andiamo via da questo inferno insieme");
            lukija.nextLine();
            int risposta;
            boolean giusta=false;
            while(giusta==false) {
            System.out.println("");  
            System.out.println("Aino: Posso fidarmi di te?");
            System.out.println("1. Certo, non ti farei mai del male");
            System.out.println("2. Parlami un pò di cosa sai sulla nave, io farò lo stesso");
            System.out.println("3. Se è per questo neanche io ti conosco...");
            System.out.println("4. Peggio per te...");
            risposta = lukija.nextInt();
            
            switch(risposta){
                case 1:System.out.println("Grazie, proverò a fidarmi di te...");
                giusta=true;
                break;
                case 2:  
                    System.out.println("Aino: Sono prigioniera da 3 settimane, mi danno da mangiare una volta al giorno");
                    System.out.println("dovrebbe esserci una nave di emergenza, controllata da un computer chissà dove...");
                    lukija.nextLine();
                    System.out.println("Tu: Hai qualche informazione sulla guardia del piano?");
                    lukija.nextLine();
                    System.out.println("Aino: la guardia che viene a portarmi del cibo l'altro giorno si è scordata una cosa");
                    System.out.println("dal posto dove la teneva a tornare qui ci ha messo 96 secondi, pertanto non dovrebbe");
                    System.out.println("essere lontano da qui, probabilmente risiede al piano di sopra");
                    lukija.nextLine();
                    System.out.println("Tu: Ottimo, grazie dell'informazione");
                     giusta=true;
                break;
                case 3: System.out.println("Aino: Allora penso proprio che ognuno andrà per la sua strada..."); 
                        System.out.println("a meno che...tu non mi dia modo di fidarmi di te.");
                        giusta=false;
                break;
                case 4: System.out.println("Aino: Hai creato chaos per liberarmi per poi lasciarmi morente qui?"); ;
                        System.out.println("onorevole da parte tua...");
                        giusta=false;
                default: System.out.println("Risposta non valida");
                giusta=false;
                break;
            }
            }
           
            
           
            
            System.out.println("Aino:Grazie per avermi liberata, ti seguirei volentieri, ma sono ferita e non posso camminare...");
            System.out.println("se tu fossi così gentile da portarmi un medikit ti seguirei volentieri");
            lukija.nextLine();
            
        }
        else if(visited == 1){
            System.out.println("Tu: Come ti senti? Non ho trovato nulla per curarti...");
            lukija.nextLine();
            System.out.println("Aino:Ho sentito le guardie parlare di un kit di pronto soccorso.");
            System.out.println("forse dovresti dare un'occhiata in giro, se tu lo trovassi potrei camminare");
            lukija.nextLine();
            
        }
        else{
            System.out.println(randomAnswer());
            lukija.nextLine();
            
        } 
            
    }
    
    
    public static void followDialogue(){
        AsciiPics.theGirl();
        if(visited == 0){
        System.out.println("Tu: Chi sei?...");
            lukija.nextLine();
            System.out.println("Donna prigioniera: Sono Aino, una prigioniera della colonia 51");
            lukija.nextLine();
            System.out.println("Tu: Vieni con me, andiamo via da questo inferno insieme");
            lukija.nextLine();
            System.out.println("Aino: Non posso camminare, ho bisogno di essere curata...mi dispiace");
            lukija.nextLine();
        System.out.println("Tu: Ho questo medikit, pensi possa essere sufficiente?");
        lukija.nextLine();
        System.out.println("Aino: Si, penso di poterti seguire fino all'uscita...ma come usciremo da qui?");
        lukija.nextLine();    
        }
        else{
         System.out.println("Aino: Si, penso di poterti seguire fino all'uscita...ma come usciremo da qui?");
         lukija.nextLine();
        }
    }
    
    
    public static String randomAnswer(){
        
      String dialogue = "";
      double d =  Math.random();
      d = d*10;
      int id = (int) Math.round(d);
       // System.out.println("id:"+id);
      
      if(id <= 2){
          return dialogue = "Aino: Per favore muoviti...mi fa male...";
      }    
      else if(id > 2 && id <= 4){
          return dialogue = "Aino: Sono sicura riusciremo a scappare insieme...\n"+
                             "Tu: Certo, mi preoccupa solo...la navetta sarà grande abbastanza?";
      }
      else if(id > 4 && id <= 6){
          return dialogue ="Aino: Ho una bella sensazione al riguardo";
      }
      else if(id > 6 && id <= 8){
          return dialogue ="Aino: La guerra...la guerra non cambia mai.";
      }
      else if(id > 8 && id <= 9){
          return dialogue = "Tu: E se...ci accoppiassimo?\n"+
                            "Aino: Ma anche no...";
      }
      else{
          return dialogue ="Aino: L'uomo giusto nel posto sbagliato può fare la differenza nel mondo.";   
      }
      
      
        
    }
    
    

    
}
