# EscapeFromSalveenee
## Presentazione dell'architettura del sistema

Avventura testuale all'interno di una "grafica" testuale, ovvero una nave dove ci si può muovere interagendo con la UI (sempre testuale) 
dove ci troviamo a cercare una via d'uscita ed a salvare una prigioniera (che a seconda dei dialoghi o degli oggetti che le porteremo verrà 
o meno con noi fuori)

Sostanzialmente il gioco è strutturato su due "piani" e gli enigmi sono basati sul trovare un oggetto ed interagire (o NON interagire) con 
i personaggi, ad esempio se verremo scoperti dalla guardia del primo piano, il gioco finirà.

Gli unici elementi grafici sono caratteri, la mappa è fatta di caratteri, le illustrazioni della prigioniera e del nemico come della nave stessa
sono composte da caratteri. Di seguito una breve descrizione delle classi con l'UML della classe principale


###### Area
Area contiene le coordinate del nostro personaggio, grazie alle coordinate possiamo muoverlo sulla mappa
e la mappa può restituirci in ogni momento le coordinate di ogni elemento
Inoltre ogni area ha un nome, che esce nella UI quando la attraversiamo

###### AsciiPics
Questa classe serve solo a "illustrare" con i caratteri alcuni momenti del gioco (il game over, la vittoria
ed il dialogo con la prigioniera. Ci sono due tipi di finali, uno in cui scappiamo soli ed uno con la prigioniera
illustrati in maniera differente

###### Bag
Bag è lo zaino, dove inseriremo gli oggetti che troveremo nell'avventura.
Un ArrayList che stampa sulla UI il nome dell'oggetto, e lo inserisce quando lo raccogliamo
e lo rimuove quando lo utilizziamo.
Contiene anche le condizioni rispetto all'utilizzo di alcuni oggetti e dialoghi annessi.
Il metodo UseItem ci permette di utilizzare l'oggetto e quindi rimuoverlo dall'inventario.

###### Dialogue
Contiene il dialogo con la prigioniera, e le varie condizioni secondo cui poi ci seguirà
Nelle avventure testuali è una componente fondamentale pertanto vi sono dei vincoli secondo cui
finchè non verrà data la risposta giusta tenendo l'oggetto corretto, la prigioniera non ci seguirà.
Se continuiamo a parlarle anche senza nessuna di queste condizioni, i dialoghi saranno scelti in maniera
randomica da un pool di risposte.

###### EscapeFromSalveenee
Contiene il main del gioco, quindi tutto parte da qui.
Costruisce differenti oggetti, tra cui la griglia dove ci andremo a muovere (che è una matrice
di char)
Inizializza il gioco con un dialogo e identifica ogni elemento sulla mappa (tra cui il personaggio
che utilizzeremo noi)
Assegna un numero ad ogni oggetto, in modo da tenerli in ordine qualora dovessimo tenerli tutti assieme.
Descrive i muri della mappa, mettendo dei controlli per non superarli.
Stampa continuamente (ad ogni nostro comando di azione inserito) l'inventario e la lista dei comandi.

###### Grid
Gestisce la mappa, inserendola in una matrice, identificandone gli elementi che possiamo calpestare 
e quelli "speciali" come oggetti o porte da aprire.
Inoltre assegna ad ogni "AREA" un nome, che nella classe Area viene gestita in modo da essere restituita
al giocatore.

###### Item
Gestisce gli oggetti a terra e nell'inventario

###### NpcGuard
Gestisce il movimento e le interazioni con la guardia, con cui se collidiamo perdiamo.

###### NpcWoman
Gestisce il movimento e le interazioni della ragazza (oggetto richiesto per essere curata
e post dialogo condizioni per seguirti per scappare)

###### Player
Dichiariamo il player, con le sue coordinate.

###### RawConsole
Permette di utilizzare la console con un solo carattere (oppure con stringhe)
Inoltre valuta se nell'input stream c'è qualsiasi tipo di errore.

###### RemoteLocks
Gestisce le interazioni "a distanza" come l'apertura di alcune porte

###### UseSquare
Contiene vari metodi utili come come l'endgame, che ci permette di concludere il gioco

###### UserInput
Gestisce gli input dell'utente, la gamelogic: la logica di gioco secondo cui ci si muove con WASD per la 
mappa, e le varie interazioni (tipo u=usa)


```
Schermate di esempio
```
![demo](/demo1.gif)


## Diagramma delle Classi più importanti

![Diagramma Classi](/uml.png)

## Struttura Algebrica [EscapeFromSalveenee]

![Struttura Algebrica 1](/specifica.png)



## Fatto con

* [Netbeans](https://netbeans.apache.org) 

## Versione
0.1 (Sulla base di questa "demo" si possono fare infiniti piani ed infiniti enigmi, è solo una impostazione
di una struttura di un avventura testuale)

## Autore

**William Vesnaver** - *Per esame di Metodi Avanzati di Programmazione* 


