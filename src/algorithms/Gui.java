/*
________________________________________________
|Comp 1555 - Computer Algorithms & modelling    |
|      London underground route finder          |
|                                               |
|Source code by: Michael Level                  |
|Implementation by: Leonardo Mendes Oliveira,   |
|                   Ankur Chaudhary,            |
|                   Kasparas Kulpavicius        |
|_______________________________________________|
*/
package algorithms;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
//import jar which enable code to autofill dropdown data on station1 and station2
//v1.6.2 - made by swinglabs
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;



public class Gui extends JFrame implements ActionListener { // the gui class extends jframe
    // to allow swing elements to be used and implements the actionlistener 
    // to allow the usage of JButtons.

    StringBuilder sb = new StringBuilder("<html><h3>Welcome to London Underground Planner</h3><br>"
            + "<h5>Plan My Jounrney is a Software Application which plans<br>"
            + "your journey on the London Underground system <br>"
            + "between one station to another station,<br>"
            + "calculating the time taken between them<br>"
            + "but also considering other factors such as<br>"
            + "walking to change lines, when train stops at each station<br>"
            + "and also peak/off-peak time.<br>"
            + ""
            + "</h5>"
            + "<br>"
            + "<br>"
            + "<br>"
            + "<br>"
            + "<br>"
            + "<br>"
            + "<br>"
            + "<h4>How to Plan Your Journey:</h4>"
            + "<br>"
            + "<h5>1 - Select departure station,<br>"
            + "2 - Select arrival station,<br>"
            + "3 - Enter the time you wish to travel.<br>"
            + "Done! Get to know your journey.</h5></html>");
            
    JButton exit = new JButton("Exit");
    JButton route = new JButton("Find My Journey");
    JLabel heading = new JLabel("<html><h2>Plan Your Journey</h2></html>");
    JLabel description = new JLabel(sb.toString());
    JLabel EnterStations = new JLabel("Enter starting station and destination station.");
    JLabel from= new JLabel("<html><h3>FROM</h3></html>");
    JLabel to= new JLabel("<html><h3>TO</h3></html>");
    JLabel time= new JLabel("<html><h4>Departure Time</h4></html>");
    
    //values for the dropdown menus
    String[] stationname={"Acton Town","Aldgate","Aldgate East","Alperton","Amersham"
    ,"Angel","Archway","Arnos Grove","Arsenal","Baker Street","Balham"
    ,"Bank","Barbican","Barking","Barkingside","Barons Court","Bayswater"
    ,"Becontree","Belsize Park","Bermondsey","Bethnal Green","Blackfriars","Blackhorse Road"
    ,"Bond Street","Borough","Boston Manor","Bounds Green","Bow Road","Brent Cross"
    ,"Brixton","Bromley by Bow","Buckhurst Hill","Burnt Oak","Caledonian Road","Camden Town"
    ,"Canada Water","Canary Wharf","Canning Town","Cannon Street","Canons Park","Chalfont And Latimer"
    ,"Chalk Farm","Chancery Lane","Charing Cross","Chesham","Chigwell","Chiswick Park"
    ,"Chorleywood","Clapham Common","Clapham North","Clapham South","Cockfosters","Colindale"
    ,"Colliers Wood","Covent Garden","Croxley","Dagenham East","Dagenham Heathway","Debden"
    ,"Dollis Hill","Ealing Broadway","Ealing Common","Earls Court","East Acton","Eastcote"
    ,"East Finchley","East Ham","East Putney","Edgware","Edgware Road","Elephant And Castle"
    ,"Elm Park","Embankment","Epping","Euston","Euston Square","Fairlop"
    ,"Farringdon","Finchley Central","Finchley Road","Finsbury Park","Finsbury Park Victoria","Fulham Broadway"
    ,"Gants Hill","Gloucester Road","Golders Green","Goldhawk Road","Goodge Street","Grange Hill"
    ,"Great Portland Street","Green Park","Greenford","Gunnersbury","Hainault","Hammersmith"
    ,"Hampstead","Hanger Lane","Harlesden","Harrow and Wealdstone","Harrow On The Hill","Hatton Cross"
    ,"Heathrow Terminals 2 and 3","Heathrow Terminal 4","Heathrow Terminal 5","Hendon Central","High Barnet","Hillingdon"
    ,"High Street Kensington","Highbury And Islington","Highgate","Hillingdon","Holborn","Holborn Central"
    ,"Holland Park","Holloway Road","Hornchurch","Hounslow Central","Hounslow East","Hounslow West"
    ,"Hyde Park Corner","Ickenham","Kennington","Kensal Green","Kensington Olympia","Kentish Town"
    ,"Kenton","Kew Gardens","Kilburn","Kilburn Park","Kings Cross St Pancras","Kingsbury"
    ,"Knightsbridge","Ladbroke Grove","Lambeth North","Lancaster Gate","Latimer Road","Leicester Square"
    ,"Leyton","Leytonstone","Liverpool Street","London Bridge","Loughton","Maida Vale"
    ,"Manor House","Mansion House","Marble Arch","Marylebone","Mile End","Mill Hill East"
    ,"Monument","MoorPark","Moorgate","Morden","Mornington Crescent","Neasden"
    ,"Newbury Park","North Acton","North Ealing","North Greenwich","North Harrow","North Wembley"
    ,"Northfields","Northolt","Nortwick Park","Northwood","Northwood Hills","Notting Hill Gate"
    ,"Oakwood","Old Street","Osterley","Oval","Oxford Circus","Paddington"
    ,"Park Royal","Parsons Green","Perivale","Piccadilly Circus","Pimlico","Pinner"
    ,"Plaistow","Preston Road","Putney Bridge","Queens Park","Queensbury","Queensway"
    ,"Ravenscourt Park","Rayners Lane","Redbridge","Regents Park","Richmond","Rickmansworth"
    ,"Roding Valley","Royal Oak","Ruislip","Ruislip Gardens","Ruislip Manor","Russell Square"
    ,"Seven Sisters","Shepherd's Bush","Shepherds Bush Market","Sloane Square","Snaresbrook","South Ealing"
    ,"South Harrow","South Kensington","South Kenton","South Ruislip","South Wimbledon","South Woodford"
    ,"Southfields","Southgate","Southwark","St James Park","St Johns Wood","St Pauls"
    ,"Stamford Brook","Stanmore","Stepney Green","Stockwell","Stonebridge Park","Stratford"
    ,"Sudbury Hill","Sudbury Town","Swiss Cottage","Temple","Theydon Bois","Tooting Bec"
    ,"Tooting Broadway","Tottenham Court Road","Tottenham Court Road","Totteridge And Whetstone","Tower Hill","Tufnell Park"
    ,"Turnham Green","Turnpike Lane","Upminster","Upminster Bridge","Upney","Upton Park"
    ,"Uxbridge","Vauxhall","Victoria","Walthamstow Central","Wanstead","Warren Street"
    ,"Warwick Avenue","Waterloo","Watford","Wembley Central","Wembley Park","West Acton"
    ,"West Brompton","West Finchley","West Ham","West Hampstead","West Harrow","West Kensington"
    ,"West Ruislip","Westbourne Park","Westminster","White City","Whitechapel","Willesden Green"
    ,"Willesden Junction","Wimbledon","Wimbledon Park","Wood Green","Wood Lane","Woodford"
    ,"Woodside Park"}; 
    
    JComboBox station1 = new JComboBox(stationname);
    JComboBox station2 = new JComboBox(stationname);
    static String[] hoursdata ={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    static JComboBox hours = new JComboBox(hoursdata);
    static String[] minsdata ={"00","15","30","45"};
    static JComboBox mins = new JComboBox(minsdata);
    static JTextPane information = new JTextPane();
    
    JScrollPane infos = new JScrollPane(information);
               
    Graph graph;

    public Gui(Graph graph) {
        this.graph = graph;

        exit.addActionListener(this); // makes the class look for actions made 
        route.addActionListener(this); // by either the 'exit' or 'route' buttons
 
        try{ // changes the default image to london underground icon.
            setIconImage(ImageIO.read(new File("./images/tube.png")));
        } catch (Exception ex){ // handles the exception if the image file is not found.
            System.out.println("Icon image not found");
        }

        
        information.setContentType("text/html"); // sets the jpane's content type to
        // text/html to allow the usage of embedded html.
        
        AutoCompleteDecorator.decorate(station1); //auto-fill feature
        AutoCompleteDecorator.decorate(station2); //auto-fill feature
        
        setLocationRelativeTo(null);//sets the location of dialog on screen as the application launches
        setLayout(null); // uses null layout for the gui.
        setSize(650,700); //static size of the window.
        setTitle("Plan Your Journey"); // static title of the application
        
               
        add(heading);
        heading.setBounds(250,2,200,40);
        add(description);
        description.setBounds(10,40,600,450);
        
        add(from);
        from.setBounds(330,45,60,50);
        add(to);
        to.setBounds(330,100,60,50);
        
        add(station1);
        station1.setBounds(400,45,180,30);
        add(station2);
        station2.setBounds(400,100,180,30);
        
        add(time);
        time.setBounds(360,145,100,30);
        add(hours);
        hours.setBounds(460,145,40,30);
        add(mins);
        mins.setBounds(500,145,40,30);
        
        add(route);
        route.setBounds(420,180,150,30);
        add(infos);
        infos.setBounds(200,220,430,360);
        information.setEditable(false);
        
        add(exit);
        exit.setBounds(270,600,60,30);
        setResizable(false); //makes the user unable to resize the window
        setVisible(true); //makes the Gui visible.
        
        getRootPane().setDefaultButton(route); // default button that reacts when
        // user presses ENTER on the keyboard.
          
    }
    
    
    
    public static Graph buildGraph() {

        Graph graph = new Graph(); // creates a new graph to be used by the algorithm. 
        
       // vertex is a node object (used in linked list data structure) that is
       // added into the graph.
        Vertex WalthamstowCentral = new Vertex("Walthamstow Central");
        Vertex BlackhorseRoad = new Vertex("Blackhorse Road");
        Vertex TottenhamHale = new Vertex("Tottenham Hale");
        Vertex SevenSisters = new Vertex("Seven Sisters");
        Vertex FinsburyPark = new Vertex("Finsbury Park");
        Vertex HighburyAndIslington = new Vertex("Highbury And Islington");
        Vertex Euston = new Vertex("Euston");
        Vertex WarrenStreet = new Vertex("Warren Street");
        Vertex OxfordCircus = new Vertex("Oxford Circus");
        Vertex GreenPark = new Vertex("Green Park");
        Vertex Victoria = new Vertex("Victoria");
        Vertex Pimlico = new Vertex("Pimlico");
        Vertex Vauxhall = new Vertex("Vauxhall");
        Vertex Stockwell = new Vertex("Stockwell");
        Vertex Brixton = new Vertex("Brixton");
        Vertex Stanmore = new Vertex("Stanmore");
        Vertex CanonsPark = new Vertex("Canons Park");
        Vertex Queensbury = new Vertex("Queensbury");
        Vertex Kingsbury = new Vertex("Kingsbury");
        Vertex WembleyPark = new Vertex("Wembley Park");
        Vertex Neasden = new Vertex("Neasden");
        Vertex DollisHill = new Vertex("Dollis Hill");
        Vertex WillesdenGreen = new Vertex("Willesden Green");
        Vertex Kilburn = new Vertex("Kilburn");
        Vertex WestHampstead = new Vertex("West Hampstead");
        Vertex FinchleyRoad = new Vertex("Finchley Road");
        Vertex SwissCottage = new Vertex("Swiss Cottage");
        Vertex StJohnsWood = new Vertex("St Johns Wood");
        Vertex BakerStreet = new Vertex("Baker Street");
        Vertex BondStreet = new Vertex("Bond Street");
        Vertex Westminster = new Vertex("Westminster");
        Vertex Waterloo = new Vertex("Waterloo");
        Vertex Southwark = new Vertex("Southwark");
        Vertex LondonBridge = new Vertex("London Bridge");
        Vertex Bermondsey = new Vertex("Bermondsey");
        Vertex CanadaWater = new Vertex("Canada Water");
        Vertex CanaryWharf = new Vertex("Canary Wharf");
        Vertex NorthGreenwich = new Vertex("North Greenwich");
        Vertex CanningTown = new Vertex("Canning Town");
        Vertex WestHam = new Vertex("West Ham");
        Vertex Stratford = new Vertex("Stratford");
        Vertex HarrowAndWealdstone = new Vertex("Harrow and Wealdstone");
        Vertex Kenton = new Vertex("Kenton");
        Vertex SouthKenton = new Vertex("South Kenton");
        Vertex NorthWembley = new Vertex("North Wembley");
        Vertex WembleyCentral = new Vertex("Wembley Central");
        Vertex StonebridgePark = new Vertex("Stonebridge Park");
        Vertex Harlesden = new Vertex("Harlesden");
        Vertex WillesdenJunction = new Vertex("Willesden Junction");
        Vertex KensalGreen = new Vertex("Kensal Green");
        Vertex QueensPark = new Vertex("Queens Park");
        Vertex KilburnPark = new Vertex("Kilburn Park");
        Vertex MaidaVale = new Vertex("Maida Vale");
        Vertex WarwickAvenue = new Vertex("Warwick Avenue");
        Vertex Paddington = new Vertex("Paddington");
        Vertex EdgwareRoad = new Vertex("Edgware Road");
        Vertex Marylebone = new Vertex("Marylebone");
        Vertex RegentsPark = new Vertex("Regents Park");
        Vertex PiccadillyCircus = new Vertex("Piccadilly Circus");
        Vertex CharingCross = new Vertex("Charing Cross");
        Vertex Embankment = new Vertex("Embankment");
        Vertex LambethNorth = new Vertex("Lambeth North");
        Vertex ElephantAndCastle = new Vertex("Elephant And Castle");
        Vertex Bank = new Vertex("Bank");
        Vertex Barkingside = new Vertex("Barkingside");
        Vertex BethnalGreen = new Vertex("Bethnal Green");
        Vertex BuckhurstHill = new Vertex("Buckhurst Hill");
        Vertex ChanceryLane = new Vertex("Chancery Lane");
        Vertex Chigwell = new Vertex("Chigwell");
        Vertex Debden = new Vertex("Debden");
        Vertex EalingBroadway = new Vertex("Ealing Broadway");
        Vertex EastActon = new Vertex("East Acton");
        Vertex Epping = new Vertex("Epping");
        Vertex Fairlop = new Vertex("Fairlop");
        Vertex GantsHill = new Vertex("Gants Hill");
        Vertex GrangeHill = new Vertex("Grange Hill");
        Vertex Greenford = new Vertex("Greenford");
        Vertex Hainault = new Vertex("Hainault");
        Vertex HangerLane = new Vertex("Hanger Lane");
        Vertex Holborn = new Vertex("Holborn");
        Vertex HollandPark = new Vertex("Holland Park");
        Vertex LancasterGate = new Vertex("Lancaster Gate");
        Vertex Leyton = new Vertex("Leyton");
        Vertex Leytonstone = new Vertex("Leytonstone");
        Vertex LiverpoolStreet = new Vertex("Liverpool Street");
        Vertex Loughton = new Vertex("Loughton");
        Vertex MarbleArch = new Vertex("Marble Arch");
        Vertex MileEnd = new Vertex("Mile End");
        Vertex NewburyPark = new Vertex("Newbury Park");
        Vertex NorthActon = new Vertex("North Acton");
        Vertex Northolt = new Vertex("Northolt");
        Vertex NottingHillGate = new Vertex("Notting Hill Gate");
        Vertex Perivale = new Vertex("Perivale");
        Vertex Queensway = new Vertex("Queensway");
        Vertex Redbridge = new Vertex("Redbridge");
        Vertex RodingValley = new Vertex("Roding Valley");
        Vertex RuislipGardens = new Vertex("Ruislip Gardens");
        Vertex ShepherdsBush = new Vertex("Shepherd's Bush");  
        Vertex Snaresbrook = new Vertex("Snaresbrook");
        Vertex SouthRuislip = new Vertex("South Ruislip");
        Vertex SouthWoodford = new Vertex("South Woodford");
        Vertex StPauls = new Vertex("St Pauls");  
        Vertex TheydonBois = new Vertex("Theydon Bois");
        Vertex TottenhamCourtRoad = new Vertex("Tottenham Court Road");
        Vertex Wanstead = new Vertex("Wanstead");
        Vertex WestActon = new Vertex("West Acton");
        Vertex WestRuislip = new Vertex("West Ruislip");
        Vertex WhiteCity = new Vertex("White City");
        Vertex Woodford = new Vertex("Woodford");
        Vertex Angel = new Vertex("Angel");
        Vertex Archway = new Vertex("Archway");
        Vertex Balham = new Vertex("Balham");
        Vertex BelsizePark = new Vertex("Belsize Park");
        Vertex Borough = new Vertex("Borough");
        Vertex BrentCross = new Vertex("Brent Cross");
        Vertex BurntOak = new Vertex("Burnt Oak");
        Vertex CamdenTown = new Vertex("Camden Town");
        Vertex ChalkFarm = new Vertex("Chalk Farm");
        Vertex ClaphamCommon = new Vertex("Clapham Common");
        Vertex ClaphamNorth = new Vertex("Clapham North");
        Vertex ClaphamSouth = new Vertex("Clapham South");
        Vertex Colindale = new Vertex("Colindale");
        Vertex ColliersWood = new Vertex("Colliers Wood");
        Vertex EastFinchley = new Vertex("East Finchley");
        Vertex Edgware = new Vertex("Edgware");
        Vertex FinchleyCentral = new Vertex("Finchley Central");
        Vertex GoldersGreen = new Vertex("Golders Green");
        Vertex GoodgeStreet = new Vertex("Goodge Street");
        Vertex Hampstead = new Vertex("Hampstead");
        Vertex HendonCentral = new Vertex("Hendon Central");
        Vertex HighBarnet = new Vertex("High Barnet");
        Vertex Highgate = new Vertex("Highgate");
        Vertex Kennington = new Vertex("Kennington");
        Vertex KentishTown = new Vertex("Kentish Town");
        Vertex LeicesterSquare = new Vertex("Leicester Square");
        Vertex MillHillEast = new Vertex("Mill Hill East");
        Vertex Moorgate = new Vertex("Moorgate");
        Vertex Morden = new Vertex("Morden");
        Vertex MorningtonCrescent = new Vertex("Mornington Crescent");
        Vertex OldStreet = new Vertex("Old Street");
        Vertex Oval = new Vertex("Oval");
        Vertex SouthWimbledon = new Vertex("South Wimbledon");
        Vertex TootingBec = new Vertex("Tooting Bec");
        Vertex TootingBroadway = new Vertex("Tooting Broadway");
        Vertex TotteridgeAndW = new Vertex("Totteridge And Whetstone");
        Vertex TufnellPark = new Vertex("Tufnell Park"); 
        Vertex WestFinchley = new Vertex("West Finchley");
        Vertex WoodsidePark = new Vertex("Woodside Park");
        Vertex ActonTown = new Vertex("Acton Town");
        Vertex AldgateEast = new Vertex("Aldgate East");
        Vertex Barking = new Vertex("Barking");
        Vertex BaronsCourt = new Vertex("Barons Court");
        Vertex Bayswater = new Vertex("Bayswater");
        Vertex Becontree = new Vertex("Becontree");
        Vertex Blackfriars = new Vertex("Blackfriars");
        Vertex BowRoad = new Vertex("Bow Road");
        Vertex BromleyByBow = new Vertex("Bromley by Bow");
        Vertex CannonStreet = new Vertex("Cannon Street");
        Vertex ChiswickPark = new Vertex("Chiswick Park");
        Vertex DagenhamEast = new Vertex("Dagenham East");
        Vertex DagenhamHeathway = new Vertex("Dagenham Heathway");
        Vertex EalingCommon = new Vertex("Ealing Common");
        Vertex EarlsCourt = new Vertex("Earls Court");
        Vertex EastHam = new Vertex("East Ham");
        Vertex EastPutney = new Vertex("East Putney");
        Vertex ElmPark = new Vertex("Elm Park");
        Vertex FulhamBroadway = new Vertex("Fulham Broadway");
        Vertex GloucesterRoad = new Vertex("Gloucester Road");
        Vertex Gunnersbury = new Vertex("Gunnersbury");
        Vertex Hammersmith = new Vertex("Hammersmith");
        Vertex HighStreetKensington = new Vertex("High Street Kensington");
        Vertex Hornchurch = new Vertex("Hornchurch");
        Vertex KensingtonOlympia = new Vertex("Kensington Olympia");
        Vertex KewGardens = new Vertex("Kew Gardens");
        Vertex MansionHouse = new Vertex("Mansion House");
        Vertex Monument = new Vertex("Monument");
        Vertex ParsonsGreen = new Vertex("Parsons Green");
        Vertex Plaistow = new Vertex("Plaistow");
        Vertex PutneyBridge = new Vertex("Putney Bridge");
        Vertex RavenscourtPark = new Vertex("Ravenscourt Park");
        Vertex Richmond = new Vertex("Richmond");
        Vertex SloaneSquare = new Vertex("Sloane Square");
        Vertex SouthKensington = new Vertex("South Kensington");
        Vertex Southfields = new Vertex("Southfields");
        Vertex StJamesPark = new Vertex("St James Park");
        Vertex StamfordBrook = new Vertex("Stamford Brook");
        Vertex StepneyGreen = new Vertex("Stepney Green");
        Vertex Temple = new Vertex("Temple");
        Vertex TowerHill = new Vertex("Tower Hill");
        Vertex TurnhamGreen = new Vertex("Turnham Green");
        Vertex Upminster = new Vertex("Upminster");
        Vertex UpminsterBridge = new Vertex("Upminster Bridge");
        Vertex Upney = new Vertex("Upney");
        Vertex UptonPark = new Vertex("Upton Park");
        Vertex WestBrompton = new Vertex("West Brompton");
        Vertex WestKensington = new Vertex("West Kensington");
        Vertex Whitechapel = new Vertex("Whitechapel");
        Vertex Wimbledon = new Vertex("Wimbledon");
        Vertex WimbledonPark = new Vertex("Wimbledon Park"); 
        Vertex Aldgate = new Vertex("Aldgate");
        Vertex Barbican = new Vertex("Barbican");
        Vertex EustonSquare = new Vertex("Euston Square");
        Vertex Farringdon = new Vertex("Farringdon");
        Vertex GoldhawkRoad = new Vertex("Goldhawk Road");
        Vertex GreatPortlandStreet = new Vertex("Great Portland Street");
        Vertex KingsCrossStPancras = new Vertex("Kings Cross St Pancras");
        Vertex LadbrokeGrove = new Vertex("Ladbroke Grove");
        Vertex LatimerRoad = new Vertex("Latimer Road");
        Vertex RoyalOak = new Vertex("Royal Oak");
        Vertex ShepherdsBushMarket = new Vertex("Shepherds Bush Market");
        Vertex WestbournePark = new Vertex("Westbourne Park");
        Vertex WoodLane = new Vertex("Wood Lane");
        Vertex Alperton = new Vertex("Alperton");
        Vertex ArnosGrove = new Vertex("Arnos Grove");
        Vertex Arsenal = new Vertex("Arsenal");
        Vertex BostonManor = new Vertex("Boston Manor");
        Vertex BoundsGreen = new Vertex("Bounds Green");
        Vertex CaledonianRoad = new Vertex("Caledonian Road");
        Vertex Cockfosters = new Vertex("Cockfosters");
        Vertex CoventGarden = new Vertex("Covent Garden");
        Vertex Eastcote = new Vertex("Eastcote");
        Vertex HattonCross = new Vertex("Hatton Cross");
        Vertex HeathrowTerminal23 = new Vertex("Heathrow Terminals 2 and 3");
        Vertex HeathrowTerminal4 = new Vertex("Heathrow Terminal 4");
        Vertex HeathrowTerminal5 = new Vertex("Heathrow Terminal 5");
        Vertex Hillingdon = new Vertex("Hillingdon");
        Vertex HolbornCentral = new Vertex("Holborn Central");
        Vertex HollowayRoad = new Vertex("Holloway Road");
        Vertex HounslowCentral = new Vertex("Hounslow Central");        
        Vertex HounslowEast = new Vertex("Hounslow East");
        Vertex HounslowWest = new Vertex("Hounslow West");
        Vertex HydeParkCorner = new Vertex("Hyde Park Corner");
        Vertex Ickenham = new Vertex("Ickenham");
        Vertex Knightsbridge = new Vertex("Knightsbridge");
        Vertex ManorHouse = new Vertex("Manor House");
        Vertex NorthEaling = new Vertex("North Ealing");
        Vertex Northfields = new Vertex("Northfields");
        Vertex Oakwood = new Vertex("Oakwood");
        Vertex Osterley = new Vertex("Osterley");
        Vertex ParkRoyal = new Vertex("Park Royal");
        Vertex RaynersLane = new Vertex("Rayners Lane");
        Vertex Ruislip = new Vertex("Ruislip");
        Vertex RuislipManor= new Vertex("Ruislip Manor");
        Vertex RussellSquare = new Vertex("Russell Square");
        Vertex SouthEaling = new Vertex("South Ealing");
        Vertex SouthHarrow = new Vertex("South Harrow");
        Vertex Southgate = new Vertex("Southgate");
        Vertex SudburyHill = new Vertex("Sudbury Hill");
        Vertex SudburyTown = new Vertex("Sudbury Town");
        Vertex TurnpikeLane = new Vertex("Turnpike Lane");
        Vertex Uxbridge = new Vertex("Uxbridge");
        Vertex WoodGreen = new Vertex("Wood Green");
        Vertex Amersham = new Vertex("Amersham");
        Vertex Chesham = new Vertex("Chesham");
        Vertex ChalfontAndLatimer = new Vertex("Chalfont And Latimer");
        Vertex Chorleywood = new Vertex("Chorleywood");
        Vertex Rickmansworth = new Vertex("Rickmansworth");
        Vertex Watford = new Vertex("Watford");
        Vertex Croxley = new Vertex("Croxley");
        Vertex WestHarrow = new Vertex("West Harrow");
        Vertex HarrowOnTheHill = new Vertex("Harrow On The Hill");
        Vertex NorthHarrow = new Vertex("North Harrow");
        Vertex Pinner = new Vertex("Pinner");
        Vertex NorthwoodHills = new Vertex("Northwood Hills");
        Vertex Northwood = new Vertex("Northwood");
        Vertex MoorPark = new Vertex("MoorPark");
        Vertex NorthwickPark = new Vertex("Nortwick Park");
        Vertex PrestonRoad = new Vertex("Preston Road"); 
        Vertex FinsburyParkVictoria = new Vertex("Finsbury Park Victoria"); 
       
        //================= GRAPH
        //adds vertices (vertex objects) into the graph. 
        graph.addVertex(WalthamstowCentral, true);
        graph.addVertex(FinsburyParkVictoria, true);
        graph.addVertex(BlackhorseRoad, true);
        graph.addVertex(TottenhamHale, true);
        graph.addVertex(SevenSisters, true);
        graph.addVertex(FinsburyPark, true);
        graph.addVertex(HighburyAndIslington, true);
        graph.addVertex(Euston, true);
        graph.addVertex(WarrenStreet, true);
        graph.addVertex(OxfordCircus, true);
        graph.addVertex(GreenPark, true);
        graph.addVertex(Victoria, true);
        graph.addVertex(Pimlico, true);
        graph.addVertex(Vauxhall, true);
        graph.addVertex(Stockwell, true);
        graph.addVertex(Brixton, true); 
        graph.addVertex(Stanmore, true);
        graph.addVertex(CanonsPark, true);
        graph.addVertex(Queensbury, true);
        graph.addVertex(Kingsbury, true);
        graph.addVertex(WembleyPark, true);
        graph.addVertex(Neasden, true);
        graph.addVertex(DollisHill, true);
        graph.addVertex(WillesdenGreen, true);
        graph.addVertex(Kilburn, true);
        graph.addVertex(WestHampstead, true);
        graph.addVertex(FinchleyRoad, true);
        graph.addVertex(SwissCottage, true);
        graph.addVertex(StJohnsWood, true);
        graph.addVertex(BakerStreet, true);
        graph.addVertex(BondStreet, true);
        graph.addVertex(Southwark, true);
        graph.addVertex(Bermondsey, true);
        graph.addVertex(CanadaWater, true);
        graph.addVertex(CanaryWharf, true);
        graph.addVertex(NorthGreenwich, true);
        graph.addVertex(CanningTown, true);
        graph.addVertex(Stratford, true);
        graph.addVertex(HarrowAndWealdstone, true);
        graph.addVertex(Kenton, true);
        graph.addVertex(SouthKenton, true);
        graph.addVertex(NorthWembley, true);
        graph.addVertex(WembleyCentral, true);
        graph.addVertex(StonebridgePark, true);
        graph.addVertex(Harlesden, true);
        graph.addVertex(WillesdenJunction, true);
        graph.addVertex(KensalGreen, true);
        graph.addVertex(QueensPark, true);
        graph.addVertex(KilburnPark, true);
        graph.addVertex(MaidaVale, true);
        graph.addVertex(WarwickAvenue, true);
        graph.addVertex(Paddington, true);
        graph.addVertex(EdgwareRoad, true);
        graph.addVertex(Marylebone, true);
        graph.addVertex(RegentsPark, true);
        graph.addVertex(PiccadillyCircus, true);
        graph.addVertex(Embankment, true);
        graph.addVertex(LambethNorth, true);
        graph.addVertex(Barkingside, true);
        graph.addVertex(BethnalGreen, true);
        graph.addVertex(BuckhurstHill, true);
        graph.addVertex(ChanceryLane, true);
        graph.addVertex(Chigwell, true);
        graph.addVertex(Debden, true);
        graph.addVertex(EalingBroadway, true);
        graph.addVertex(EastActon, true);
        graph.addVertex(Epping, true);
        graph.addVertex(Fairlop, true);
        graph.addVertex(GantsHill, true);
        graph.addVertex(GrangeHill, true);
        graph.addVertex(Greenford, true);
        graph.addVertex(Hainault, true);
        graph.addVertex(HangerLane, true);
        graph.addVertex(Holborn, true);
        graph.addVertex(HollandPark, true);
        graph.addVertex(LancasterGate, true);
        graph.addVertex(Leyton, true);
        graph.addVertex(Leytonstone, true);
        graph.addVertex(LiverpoolStreet, true);
        graph.addVertex(Loughton, true);
        graph.addVertex(MarbleArch, true);
        graph.addVertex(MileEnd, true);
        graph.addVertex(NewburyPark, true);
        graph.addVertex(NorthActon, true);
        graph.addVertex(Northolt, true);
        graph.addVertex(NottingHillGate, true);
        graph.addVertex(Perivale, true);
        graph.addVertex(Queensway, true);
        graph.addVertex(Redbridge, true);
        graph.addVertex(RodingValley, true);
        graph.addVertex(RuislipGardens, true);
        graph.addVertex(ShepherdsBush, true);
        graph.addVertex(Snaresbrook, true);
        graph.addVertex(SouthRuislip, true);
        graph.addVertex(StPauls, true);
        graph.addVertex(TheydonBois, true);
        graph.addVertex(Wanstead, true);
        graph.addVertex(WestActon, true);
        graph.addVertex(WestRuislip, true);
        graph.addVertex(WhiteCity, true);
        graph.addVertex(Woodford, true);
        graph.addVertex(Angel, true);
        graph.addVertex(Archway, true);
        graph.addVertex(Balham, true);
        graph.addVertex(Bank, true);
        graph.addVertex(BelsizePark, true);
        graph.addVertex(Borough, true);
        graph.addVertex(BrentCross, true);
        graph.addVertex(BurntOak, true);
        graph.addVertex(CamdenTown, true);
        graph.addVertex(ChalkFarm, true);
        graph.addVertex(CharingCross, true);
        graph.addVertex(ClaphamCommon, true);
        graph.addVertex(ClaphamNorth, true);
        graph.addVertex(ClaphamSouth, true);
        graph.addVertex(Colindale, true);
        graph.addVertex(ColliersWood, true);
        graph.addVertex(EastFinchley, true);
        graph.addVertex(Edgware, true);
        graph.addVertex(ElephantAndCastle, true);
        graph.addVertex(FinchleyCentral, true);
        graph.addVertex(GoldersGreen, true);
        graph.addVertex(GoodgeStreet, true);
        graph.addVertex(Hampstead, true);
        graph.addVertex(HendonCentral, true);
        graph.addVertex(HighBarnet, true);
        graph.addVertex(Kennington, true);
        graph.addVertex(KentishTown, true);
        graph.addVertex(Highgate, true);
        graph.addVertex(LeicesterSquare, true);
        graph.addVertex(LondonBridge, true);
        graph.addVertex(MillHillEast, true);
        graph.addVertex(Morden, true);
        graph.addVertex(MorningtonCrescent, true);
        graph.addVertex(OldStreet, true);
        graph.addVertex(Oval, true);
        graph.addVertex(SouthWimbledon, true);
        graph.addVertex(SouthWoodford, true);
        graph.addVertex(TootingBec, true);
        graph.addVertex(TootingBroadway, true);
        graph.addVertex(TottenhamCourtRoad, true);
        graph.addVertex(TotteridgeAndW, true);
        graph.addVertex(TufnellPark, true);
        graph.addVertex(Waterloo, true);
        graph.addVertex(WestFinchley, true);
        graph.addVertex(WoodsidePark, true);
        graph.addVertex(ActonTown, true);
        graph.addVertex(AldgateEast, true);
        graph.addVertex(Barking, true);
        graph.addVertex(BaronsCourt, true);
        graph.addVertex(Becontree, true);
        graph.addVertex(BowRoad, true);
        graph.addVertex(BromleyByBow, true);
        graph.addVertex(ChiswickPark, true);
        graph.addVertex(DagenhamEast, true);
        graph.addVertex(DagenhamHeathway, true);
        graph.addVertex(EalingCommon, true);
        graph.addVertex(EarlsCourt, true);
        graph.addVertex(EastHam, true);
        graph.addVertex(EastPutney, true);
        graph.addVertex(ElmPark, true);
        graph.addVertex(FulhamBroadway, true);
        graph.addVertex(Gunnersbury, true);
        graph.addVertex(Hornchurch, true);
        graph.addVertex(KensingtonOlympia, true);
        graph.addVertex(KewGardens, true);
        graph.addVertex(ParsonsGreen, true);
        graph.addVertex(Plaistow, true);
        graph.addVertex(PutneyBridge, true);
        graph.addVertex(RavenscourtPark, true);
        graph.addVertex(Richmond, true);
        graph.addVertex(Southfields, true);
        graph.addVertex(StamfordBrook, true);
        graph.addVertex(StepneyGreen, true);
        graph.addVertex(TurnhamGreen, true);
        graph.addVertex(Upminster, true);
        graph.addVertex(UpminsterBridge, true);
        graph.addVertex(Upney, true);
        graph.addVertex(UptonPark, true);
        graph.addVertex(WestBrompton, true);
        graph.addVertex(WestHam, true);
        graph.addVertex(WestKensington, true);
        graph.addVertex(Westminster, true);
        graph.addVertex(Whitechapel, true);
        graph.addVertex(Wimbledon, true);
        graph.addVertex(WimbledonPark, true);     
        graph.addVertex(Bayswater, true);
        graph.addVertex(Blackfriars, true);
        graph.addVertex(CannonStreet, true);
        graph.addVertex(GloucesterRoad, true);
        graph.addVertex(GoldhawkRoad, true);
        graph.addVertex(Hammersmith, true);
        graph.addVertex(HighStreetKensington, true);
        graph.addVertex(KingsCrossStPancras, true);
        graph.addVertex(LadbrokeGrove, true);
        graph.addVertex(LatimerRoad, true);
        graph.addVertex(MansionHouse, true);
        graph.addVertex(Monument, true);
        graph.addVertex(RoyalOak, true);
        graph.addVertex(ShepherdsBushMarket, true);
        graph.addVertex(SloaneSquare, true);
        graph.addVertex(SouthKensington, true);
        graph.addVertex(StJamesPark, true);
        graph.addVertex(Temple, true);
        graph.addVertex(TowerHill, true);
        graph.addVertex(WestbournePark, true);
        graph.addVertex(WoodLane, true);
        graph.addVertex(Alperton, true);
        graph.addVertex(ArnosGrove, true);
        graph.addVertex(Arsenal, true);
        graph.addVertex(BostonManor, true);
        graph.addVertex(BoundsGreen, true);
        graph.addVertex(CaledonianRoad, true);
        graph.addVertex(Cockfosters, true);
        graph.addVertex(CoventGarden, true);
        graph.addVertex(Eastcote, true);
        graph.addVertex(HattonCross, true);
        graph.addVertex(HeathrowTerminal23, true);
        graph.addVertex(HeathrowTerminal4, true);
        graph.addVertex(HeathrowTerminal5, true);
        graph.addVertex(HolbornCentral, true);
        graph.addVertex(HollowayRoad, true);
        graph.addVertex(HounslowCentral, true);
        graph.addVertex(HounslowEast, true);
        graph.addVertex(HounslowWest, true);
        graph.addVertex(HydeParkCorner, true);
        graph.addVertex(Knightsbridge, true);
        graph.addVertex(ManorHouse, true);
        graph.addVertex(NorthEaling, true);
        graph.addVertex(Northfields, true);
        graph.addVertex(Oakwood, true);
        graph.addVertex(Osterley, true);
        graph.addVertex(ParkRoyal, true);
        graph.addVertex(RussellSquare, true);
        graph.addVertex(SouthEaling, true);
        graph.addVertex(SouthHarrow, true);
        graph.addVertex(Southgate, true);
        graph.addVertex(SudburyHill, true);
        graph.addVertex(SudburyTown, true);
        graph.addVertex(TurnpikeLane, true);
        graph.addVertex(WoodGreen, true);
        graph.addVertex(Amersham, true);
        graph.addVertex(Chesham, true);
        graph.addVertex(ChalfontAndLatimer, true);
        graph.addVertex(Chorleywood, true);
        graph.addVertex(Rickmansworth, true);
        graph.addVertex(Watford, true);
        graph.addVertex(Croxley, true);
        graph.addVertex(Uxbridge, true);
        graph.addVertex(Hillingdon, true);
        graph.addVertex(Ickenham, true);
        graph.addVertex(Ruislip, true);
        graph.addVertex(RuislipManor, true);
        graph.addVertex(RaynersLane, true);
        graph.addVertex(WestHarrow, true);
        graph.addVertex(HarrowOnTheHill, true);
        graph.addVertex(NorthHarrow, true);
        graph.addVertex(Pinner, true);
        graph.addVertex(NorthwoodHills, true);
        graph.addVertex(Northwood, true);
        graph.addVertex(MoorPark, true);
        graph.addVertex(NorthwickPark, true);
        graph.addVertex(PrestonRoad, true);
        graph.addVertex(GreatPortlandStreet, true);
        graph.addVertex(EustonSquare, true);
        graph.addVertex(Farringdon, true);
        graph.addVertex(Barbican, true);
        graph.addVertex(Moorgate, true);
        graph.addVertex(Aldgate, true);

       
       
        //fix wrong result euston to warren st as it picked victoria which is bigger
        graph.addEdge(Euston, WarrenStreet, "Northern", 1);
       
        //=================== EDGES
        // new Edge(STATION.A, STATION.B, "LINE", TIME);
        Edge[] edges = new Edge[376]; // DON'T FORGET TO CHANGE THE ARRAY SIZE DEPENDING ON THE AMOUNT OF EDGES
        // OTHERWISE THERE WILL BE AN OUT OF BOUNDS EXCEPTION
        
        
        //victoria 
        edges[0] = new Edge(WalthamstowCentral, BlackhorseRoad, "Victoria", 3);
        edges[1] = new Edge(BlackhorseRoad, TottenhamHale, "Victoria", 3);
        edges[2] = new Edge(TottenhamHale, SevenSisters, "Victoria", 2);
        edges[3] = new Edge(SevenSisters, FinsburyPark, "Victoria", 5);
        edges[4] = new Edge(FinsburyPark, HighburyAndIslington, "Victoria", 2);
        edges[5] = new Edge(HighburyAndIslington, KingsCrossStPancras, "Victoria", 3);
        edges[6] = new Edge(KingsCrossStPancras, Euston, "Victoria", 2);
        edges[7] = new Edge(Euston, WarrenStreet, "Victoria", 2);
        edges[8] = new Edge(WarrenStreet, OxfordCircus, "Victoria", 2);
        edges[9] = new Edge(OxfordCircus, GreenPark, "Victoria", 2);
        edges[10] = new Edge(GreenPark, Victoria, "Victoria", 2);
        edges[11] = new Edge(Victoria, Pimlico, "Victoria", 2);
        edges[12] = new Edge(Pimlico, Vauxhall, "Victoria", 2);
        edges[13] = new Edge(Vauxhall, Stockwell, "Victoria", 3);
        edges[14] = new Edge(Stockwell, Brixton, "Victoria", 3);

        //jubilee 
        edges[15] = new Edge(Stanmore, CanonsPark, "Jubilee", 4);
        edges[16] = new Edge(CanonsPark, Queensbury, "Jubilee", 3);
        edges[17] = new Edge(Queensbury, Kingsbury, "Jubilee", 2);
        edges[18] = new Edge(Kingsbury, WembleyPark, "Jubilee", 3);
        edges[19] = new Edge(WembleyPark, Neasden, "Jubilee", 4);
        edges[20] = new Edge(Neasden, DollisHill, "Jubilee", 2);
        edges[21] = new Edge(DollisHill, WillesdenGreen, "Jubilee", 2);
        edges[22] = new Edge(WillesdenGreen, Kilburn, "Jubilee", 2);
        edges[23] = new Edge(Kilburn, WestHampstead, "Jubilee", 2);
        edges[24] = new Edge(WestHampstead, FinchleyRoad, "Jubilee", 1);
        edges[25] = new Edge(FinchleyRoad, SwissCottage, "Jubilee", 2);
        edges[26] = new Edge(SwissCottage, StJohnsWood, "Jubilee", 2);
        edges[27] = new Edge(StJohnsWood, BakerStreet, "Jubilee", 3);
        edges[28] = new Edge(BakerStreet, BondStreet, "Jubilee", 2);
        edges[29] = new Edge(BondStreet, GreenPark, "Jubilee", 2);
        edges[30] = new Edge(GreenPark, Westminster, "Jubilee", 2);
        edges[31] = new Edge(Westminster, Waterloo, "Jubilee", 2);
        edges[32] = new Edge(Waterloo, Southwark, "Jubilee", 2);
        edges[33] = new Edge(Southwark, LondonBridge, "Jubilee", 3);
        edges[34] = new Edge(LondonBridge, Bermondsey, "Jubilee", 2);
        edges[35] = new Edge(Bermondsey, CanadaWater, "Jubilee", 2);
        edges[36] = new Edge(CanadaWater, CanaryWharf, "Jubilee", 3);
        edges[37] = new Edge(CanaryWharf, NorthGreenwich, "Jubilee", 3);
        edges[38] = new Edge(NorthGreenwich, CanningTown, "Jubilee", 3);
        edges[39] = new Edge(CanningTown, WestHam, "Jubilee", 3);
        edges[40] = new Edge(WestHam, Stratford, "Jubilee", 3);
        
        //bakerloo 
        edges[41] = new Edge(Kenton, SouthKenton, "Bakerloo", 2);
        edges[42] = new Edge(SouthKenton, NorthWembley, "Bakerloo", 2);
        edges[43] = new Edge(NorthWembley, WembleyCentral, "Bakerloo", 2);
        edges[44] = new Edge(WembleyCentral, StonebridgePark, "Bakerloo", 2);
        edges[45] = new Edge(StonebridgePark, Harlesden, "Bakerloo", 2);
        edges[46] = new Edge(Harlesden, WillesdenJunction, "Bakerloo", 2);
        edges[47] = new Edge(WillesdenJunction, KensalGreen, "Bakerloo", 3);
        edges[48] = new Edge(KensalGreen, QueensPark, "Bakerloo", 2);
        edges[49] = new Edge(QueensPark, KilburnPark, "Bakerloo", 2);
        edges[50] = new Edge(KilburnPark, MaidaVale, "Bakerloo", 2);
        edges[51] = new Edge(MaidaVale, WarwickAvenue, "Bakerloo", 1);
        edges[52] = new Edge(WarwickAvenue, Paddington, "Bakerloo", 2);
        edges[53] = new Edge(Paddington, EdgwareRoad, "Bakerloo", 2);
        edges[54] = new Edge(EdgwareRoad, Marylebone, "Bakerloo", 1);
        edges[55] = new Edge(Marylebone, BakerStreet, "Bakerloo", 2);
        edges[56] = new Edge(BakerStreet, RegentsPark, "Bakerloo", 2);
        edges[57] = new Edge(RegentsPark, OxfordCircus, "Bakerloo", 2);
        edges[58] = new Edge(OxfordCircus, PiccadillyCircus, "Bakerloo", 2);
        edges[59] = new Edge(PiccadillyCircus, CharingCross, "Bakerloo", 2);
        edges[60] = new Edge(CharingCross, Embankment, "Bakerloo", 1);
        edges[61] = new Edge(Embankment, Waterloo, "Bakerloo", 2);
        edges[62] = new Edge(Waterloo, LambethNorth, "Bakerloo", 2);
        edges[63] = new Edge(LambethNorth, ElephantAndCastle, "Bakerloo", 3);
               
        //Metropolitan 
        edges[64] = new Edge(Amersham, ChalfontAndLatimer, "Metropolitan", 4);
        edges[65] = new Edge(Chesham, ChalfontAndLatimer, "Metropolitan", 9);
        edges[66] = new Edge(ChalfontAndLatimer, Chorleywood, "Metropolitan", 4);
        edges[67] = new Edge(Chorleywood, Rickmansworth, "Metropolitan", 4);
        edges[68] = new Edge(Rickmansworth, MoorPark, "Metropolitan", 5);
        edges[69] = new Edge(Watford, Croxley, "Metropolitan", 4);
        edges[70] = new Edge(Croxley, MoorPark, "Metropolitan", 4);
        edges[71] = new Edge(Uxbridge, Hillingdon, "Metropolitan", 4);
        edges[72] = new Edge(Hillingdon, Ickenham, "Metropolitan", 2);
        edges[73] = new Edge(Ickenham, Ruislip, "Metropolitan", 2);
        edges[74] = new Edge(Ruislip, RuislipManor, "Metropolitan", 2);
        edges[75] = new Edge(RuislipManor, Eastcote, "Metropolitan", 2);
        edges[76] = new Edge(Eastcote, RaynersLane, "Metropolitan", 2);
        edges[77] = new Edge(RaynersLane, WestHarrow, "Metropolitan", 3);
        edges[78] = new Edge(WestHarrow, HarrowOnTheHill, "Metropolitan", 2);
        edges[79] = new Edge(HarrowOnTheHill, NorthHarrow, "Metropolitan", 3);
        edges[80] = new Edge(NorthHarrow, Pinner, "Metropolitan", 2);
        edges[81] = new Edge(Pinner, NorthwoodHills, "Metropolitan", 3);
        edges[82] = new Edge(NorthwoodHills, Northwood, "Metropolitan", 3);
        edges[83] = new Edge(Northwood, MoorPark, "Metropolitan", 3);
        edges[84] = new Edge(MoorPark, HarrowOnTheHill, "Metropolitan", 14);
        edges[85] = new Edge(HarrowOnTheHill, FinchleyRoad, "Metropolitan", 16);
        edges[86] = new Edge(HarrowOnTheHill, WembleyPark, "Metropolitan", 9);
        edges[87] = new Edge(HarrowOnTheHill, NorthwickPark, "Metropolitan", 3);
        edges[88] = new Edge(NorthwickPark, PrestonRoad, "Metropolitan", 3);
        edges[89] = new Edge(PrestonRoad, WembleyPark, "Metropolitan", 3);
        edges[90] = new Edge(WembleyPark, FinchleyRoad, "Metropolitan", 7);
        edges[91] = new Edge(FinchleyRoad, BakerStreet, "Metropolitan", 5);
        edges[92] = new Edge(BakerStreet, GreatPortlandStreet, "Metropolitan", 2);
        edges[93] = new Edge(GreatPortlandStreet, EustonSquare, "Metropolitan", 2);
        edges[94] = new Edge(EustonSquare, KingsCrossStPancras, "Metropolitan", 2);
        edges[95] = new Edge(KingsCrossStPancras, Farringdon, "Metropolitan", 2);
        edges[96] = new Edge(Farringdon, Barbican, "Metropolitan", 4);
        edges[97] = new Edge(Barbican, Moorgate, "Metropolitan", 2);
        edges[98] = new Edge(Moorgate, LiverpoolStreet, "Metropolitan", 2);
        edges[99] = new Edge(LiverpoolStreet, Aldgate, "Metropolitan", 3);
       
        //Waterloo & City 
        edges[100] = new Edge(Bank, Waterloo, "Waterloo & City", 5);
             
        //Hammersmith & City
        edges[101] = new Edge(Barking, EastHam, "Hammersmith and City", 4);
        edges[102] = new Edge(EastHam, UptonPark, "Hammersmith and City", 2);
        edges[103] = new Edge(UptonPark, Plaistow, "Hammersmith and City", 2);
        edges[104] = new Edge(Plaistow, WestHam, "Hammersmith and City", 2);
        edges[105] = new Edge(WestHam, BromleyByBow, "Hammersmith and City", 2);
        edges[106] = new Edge(BromleyByBow, BowRoad, "Hammersmith and City", 2);
        edges[107] = new Edge(BowRoad, MileEnd, "Hammersmith and City", 2);
        edges[108] = new Edge(MileEnd, StepneyGreen, "Hammersmith and City", 2);
        edges[109] = new Edge(StepneyGreen, Whitechapel, "Hammersmith and City", 3);
        edges[110] = new Edge(Whitechapel, AldgateEast, "Hammersmith and City", 2);
        edges[111] = new Edge(AldgateEast, LiverpoolStreet, "Hammersmith and City", 3);
        edges[112] = new Edge(LiverpoolStreet, Moorgate, "Hammersmith and City", 2);
        edges[113] = new Edge(Moorgate, Barbican, "Hammersmith and City", 2);
        edges[114] = new Edge(Barbican, Farringdon, "Hammersmith and City", 1);
        edges[115] = new Edge(Farringdon, KingsCrossStPancras, "Hammersmith and City", 4);
        edges[116] = new Edge(KingsCrossStPancras, EustonSquare, "Hammersmith and City", 2);
        edges[117] = new Edge(EustonSquare, GreatPortlandStreet, "Hammersmith and City", 2);
        edges[118] = new Edge(GreatPortlandStreet, BakerStreet, "Hammersmith and City", 2);
        edges[119] = new Edge(BakerStreet, EdgwareRoad, "Hammersmith and City", 3);
        edges[120] = new Edge(EdgwareRoad, Paddington, "Hammersmith and City", 3);
        edges[121] = new Edge(Paddington, RoyalOak, "Hammersmith and City", 2);
        edges[122] = new Edge(RoyalOak, WestbournePark, "Hammersmith and City", 2);
        edges[123] = new Edge(WestbournePark, LadbrokeGrove, "Hammersmith and City", 2);
        edges[124] = new Edge(LadbrokeGrove, LatimerRoad, "Hammersmith and City", 2);
        edges[125] = new Edge(LatimerRoad, WoodLane, "Hammersmith and City", 2);
        edges[126] = new Edge(WoodLane, ShepherdsBushMarket, "Hammersmith and City", 2);
        edges[127] = new Edge(ShepherdsBushMarket, GoldhawkRoad, "Hammersmith and City", 1);
        edges[128] = new Edge(GoldhawkRoad, Hammersmith, "Hammersmith and City", 2);

        //Circle
        edges[129] = new Edge(EdgwareRoad, Paddington, "Circle", 3);
        edges[130] = new Edge(Paddington, Bayswater, "Circle", 2);
        edges[131] = new Edge(Bayswater, NottingHillGate, "Circle", 2);
        edges[132] = new Edge(NottingHillGate, HighStreetKensington, "Circle", 2);
        edges[133] = new Edge(HighStreetKensington, GloucesterRoad, "Circle", 3);
        edges[134] = new Edge(GloucesterRoad, SouthKensington, "Circle", 3);
        edges[135] = new Edge(SouthKensington, SloaneSquare, "Circle", 2);
        edges[136] = new Edge(SloaneSquare, Victoria, "Circle", 2);
        edges[137] = new Edge(Victoria, StJamesPark, "Circle", 2);
        edges[138] = new Edge(StJamesPark, Westminster, "Circle", 2);
        edges[139] = new Edge(Westminster, Embankment, "Circle", 1);
        edges[140] = new Edge(Embankment, Temple, "Circle", 2);
        edges[141] = new Edge(Temple, Blackfriars, "Circle", 1);
        edges[142] = new Edge(Blackfriars, MansionHouse, "Circle", 2);
        edges[143] = new Edge(MansionHouse, CannonStreet, "Circle", 2);
        edges[144] = new Edge(CannonStreet, Monument, "Circle", 1);
        edges[145] = new Edge(Monument, TowerHill, "Circle", 2);
        edges[146] = new Edge(TowerHill, Aldgate, "Circle", 2);
        edges[147] = new Edge(Aldgate, LiverpoolStreet, "Circle", 3);
        edges[148] = new Edge(LiverpoolStreet, Moorgate, "Circle", 2);
        edges[149] = new Edge(Moorgate, Barbican, "Circle", 2);
        edges[150] = new Edge(Barbican, Farringdon, "Circle", 1);
        edges[151] = new Edge(Farringdon, KingsCrossStPancras, "Circle", 4);
        edges[152] = new Edge(KingsCrossStPancras, EustonSquare, "Circle", 2);
        edges[153] = new Edge(EustonSquare, GreatPortlandStreet, "Circle", 2);
        edges[154] = new Edge(GreatPortlandStreet, BakerStreet, "Circle", 2);
        edges[155] = new Edge(BakerStreet, EdgwareRoad, "Circle", 3);
        edges[156] = new Edge(Paddington, RoyalOak, "Circle", 2);
        edges[157] = new Edge(RoyalOak, WestbournePark, "Circle", 2);
        edges[158] = new Edge(WestbournePark, LadbrokeGrove, "Circle", 2);
        edges[159] = new Edge(LadbrokeGrove, LatimerRoad, "Circle", 2);
        edges[160] = new Edge(LatimerRoad, WoodLane, "Circle", 2);
        edges[161] = new Edge(WoodLane, ShepherdsBushMarket, "Circle", 2);
        edges[162] = new Edge(ShepherdsBushMarket, GoldhawkRoad, "Circle", 2);
        edges[163] = new Edge(GoldhawkRoad, Hammersmith, "Circle", 2);
        
        
        //Central
        edges[164] = new Edge(Epping, TheydonBois, "Central", 3);
        edges[165] = new Edge(TheydonBois, Debden, "Central", 3);
        edges[166] = new Edge(Debden, Loughton, "Central", 2);
        edges[167] = new Edge(Loughton, BuckhurstHill, "Central", 3);
        edges[168] = new Edge(BuckhurstHill, Woodford, "Central", 2);
        edges[169] = new Edge(Woodford, SouthWoodford, "Central", 3);
        edges[170] = new Edge(SouthWoodford, Snaresbrook, "Central", 2);
        edges[171] = new Edge(Snaresbrook, Leytonstone, "Central", 2);
        edges[172] = new Edge(RodingValley, Woodford, "Central", 4);
        edges[173] = new Edge(RodingValley, Chigwell, "Central", 3);
        edges[174] = new Edge(Chigwell, GrangeHill, "Central", 2);
        edges[175] = new Edge(GrangeHill, Hainault, "Central", 5);
        edges[176] = new Edge(Hainault, Fairlop, "Central", 2);
        edges[177] = new Edge(Fairlop, Barkingside, "Central", 2);
        edges[178] = new Edge(Barkingside, NewburyPark, "Central", 2);
        edges[179] = new Edge(NewburyPark, GantsHill, "Central", 3);
        edges[180] = new Edge(GantsHill, Redbridge, "Central", 2);
        edges[181] = new Edge(Redbridge, Wanstead, "Central", 2);
        edges[182] = new Edge(Wanstead, Leytonstone, "Central", 2);
        edges[183] = new Edge(Leytonstone, Leyton, "Central", 2);
        edges[184] = new Edge(Leyton, Stratford, "Central", 3);
        edges[185] = new Edge(Stratford, MileEnd, "Central", 4);
        edges[186] = new Edge(MileEnd, BethnalGreen, "Central", 2);
        edges[187] = new Edge(BethnalGreen, LiverpoolStreet, "Central", 3);
        edges[188] = new Edge(LiverpoolStreet, Bank, "Central", 2);
        edges[189] = new Edge(Bank, StPauls, "Central", 2);
        edges[190] = new Edge(StPauls, ChanceryLane, "Central", 2);
        edges[191] = new Edge(ChanceryLane, Holborn, "Central", 1);
        edges[192] = new Edge(Holborn, TottenhamCourtRoad, "Central", 2);
        edges[193] = new Edge(TottenhamCourtRoad, OxfordCircus, "Central", 1);
        edges[194] = new Edge(OxfordCircus, BondStreet, "Central", 2);
        edges[195] = new Edge(BondStreet, MarbleArch, "Central", 2);
        edges[196] = new Edge(MarbleArch, LancasterGate, "Central", 3);
        edges[197] = new Edge(LancasterGate, Queensway, "Central", 2);
        edges[198] = new Edge(Queensway, NottingHillGate, "Central", 2);
        edges[199] = new Edge(NottingHillGate, HollandPark, "Central", 1);
        edges[200] = new Edge(HollandPark, ShepherdsBush, "Central", 2);
        edges[201] = new Edge(ShepherdsBush, WhiteCity, "Central", 3);
        edges[202] = new Edge(WhiteCity, EastActon, "Central", 3);
        edges[203] = new Edge(EastActon, NorthActon, "Central", 2);
        edges[204] = new Edge(NorthActon, HangerLane, "Central", 3);
        edges[205] = new Edge(NorthActon, WestActon, "Central", 2);
        edges[206] = new Edge(WestActon, EalingBroadway, "Central", 3);
        edges[207] = new Edge(HangerLane, Perivale, "Central", 3);
        edges[208] = new Edge(Perivale, Greenford, "Central", 2);
        edges[209] = new Edge(Greenford, Northolt, "Central", 2);
        edges[210] = new Edge(Northolt, SouthRuislip, "Central", 3);
        edges[211] = new Edge(SouthRuislip, RuislipGardens, "Central", 2);
        edges[212] = new Edge(RuislipGardens, WestRuislip, "Central", 2);
        
        //District
        edges[213] = new Edge(Upminster, UpminsterBridge, "District", 2);
        edges[214] = new Edge(UpminsterBridge, Hornchurch, "District", 2);
        edges[215] = new Edge(Hornchurch, ElmPark, "District", 2);
        edges[216] = new Edge(ElmPark, DagenhamEast, "District", 3);
        edges[217] = new Edge(DagenhamEast, DagenhamHeathway, "District", 2);
        edges[218] = new Edge(DagenhamHeathway, Becontree, "District", 3);
        edges[219] = new Edge(Becontree, Upney, "District", 2);
        edges[220] = new Edge(Upney, Barking, "District", 3);
        edges[221] = new Edge(Barking, EastHam, "District", 4);
        edges[222] = new Edge(EastHam, UptonPark, "District", 2);
        edges[223] = new Edge(UptonPark, Plaistow, "District", 2);
        edges[224] = new Edge(Plaistow, WestHam, "District", 2);
        edges[225] = new Edge(WestHam, BromleyByBow, "District", 2);
        edges[226] = new Edge(BromleyByBow, BowRoad, "District", 2);
        edges[227] = new Edge(BowRoad, MileEnd, "District", 2);
        edges[228] = new Edge(MileEnd, StepneyGreen, "District", 2);
        edges[229] = new Edge(StepneyGreen, Whitechapel, "District", 3);
        edges[230] = new Edge(Whitechapel, AldgateEast, "District", 2);
        edges[231] = new Edge(AldgateEast, TowerHill, "District", 3);
        edges[232] = new Edge(TowerHill, Monument, "District", 2);
        edges[233] = new Edge(Monument, CannonStreet, "District", 1);
        edges[234] = new Edge(CannonStreet,MansionHouse,"District", 2);
        edges[235] = new Edge(MansionHouse, Blackfriars, "District", 2);
        edges[236] = new Edge(Blackfriars, Temple, "District", 1);
        edges[237] = new Edge(Temple, Embankment, "District", 2);
        edges[238] = new Edge(Embankment, Westminster, "District", 1);
        edges[239] = new Edge(Westminster, StJamesPark, "District", 2);
        edges[240] = new Edge(StJamesPark, Victoria, "District", 2);
        edges[241] = new Edge(Victoria, SloaneSquare, "District", 2);
        edges[242] = new Edge(SloaneSquare, SouthKensington, "District", 2);
        edges[243] = new Edge(SouthKensington, GloucesterRoad, "District", 3);
        edges[244] = new Edge(GloucesterRoad, EarlsCourt, "District", 2);
        edges[245] = new Edge(EarlsCourt, KensingtonOlympia,"District", 3);
        edges[246] = new Edge(EarlsCourt, HighStreetKensington, "District", 5);
        edges[247] = new Edge(HighStreetKensington, NottingHillGate, "District", 2);
        edges[248] = new Edge(NottingHillGate, Bayswater, "District", 2);
        edges[249] = new Edge(Bayswater, Paddington, "District", 2);
        edges[250] = new Edge(Paddington, EdgwareRoad, "District", 3);
        edges[251] = new Edge(EarlsCourt, WestBrompton, "District", 2);
        edges[252] = new Edge(WestBrompton, FulhamBroadway, "District", 2);
        edges[253] = new Edge(FulhamBroadway, ParsonsGreen, "District", 2);
        edges[254] = new Edge(ParsonsGreen, PutneyBridge, "District", 3);
        edges[255] = new Edge(PutneyBridge, EastPutney, "District", 3);
        edges[256] = new Edge(EastPutney, Southfields, "District", 2);
        edges[257] = new Edge(Southfields, WimbledonPark, "District", 2);
        edges[258] = new Edge(WimbledonPark, Wimbledon, "District", 4);
        edges[259] = new Edge(EarlsCourt, WestKensington, "District", 2);
        edges[260] = new Edge(WestKensington, BaronsCourt, "District", 2);
        edges[261] = new Edge(BaronsCourt, Hammersmith, "District", 3);
        edges[262] = new Edge(Hammersmith, RavenscourtPark, "District", 2);
        edges[263] = new Edge(RavenscourtPark, StamfordBrook, "District", 2);
        edges[264] = new Edge(StamfordBrook, TurnhamGreen, "District", 1);
        edges[265] = new Edge(TurnhamGreen, Gunnersbury, "District", 3);
        edges[266] = new Edge(Gunnersbury, KewGardens, "District", 2);
        edges[267] = new Edge(KewGardens, Richmond, "District", 4);
        edges[268] = new Edge(TurnhamGreen, ChiswickPark, "District", 2);
        edges[269] = new Edge(ChiswickPark, ActonTown, "District", 2);
        edges[270] = new Edge(ActonTown, EalingCommon, "District", 2);
        edges[271] = new Edge(EalingCommon, EalingBroadway, "District", 5);
        
        //======== NORTHERN
        edges[272] = new Edge(HighBarnet, TotteridgeAndW, "Northern", 4);
        edges[273] = new Edge(TotteridgeAndW, WoodsidePark, "Northern", 2);
        edges[274] = new Edge(WoodsidePark, WestFinchley,"Northern", 2);
        edges[275] = new Edge(WestFinchley, FinchleyCentral, "Northern", 2);
        edges[276] = new Edge(MillHillEast, FinchleyCentral,"Northern", 4);
        edges[277] = new Edge(FinchleyCentral, EastFinchley, "Northern", 4);
        edges[278] = new Edge(EastFinchley, Highgate, "Northern", 3);
        edges[279] = new Edge(Highgate, Archway, "Northern", 3);
        edges[280] = new Edge(Archway, TufnellPark, "Northern", 2);
        edges[281] = new Edge(TufnellPark, KentishTown, "Northern", 1);
        edges[282] = new Edge(KentishTown, CamdenTown, "Northern", 2);
        edges[283] = new Edge(Edgware, BurntOak, "Northern", 4);
        edges[284] = new Edge(BurntOak, Colindale, "Northern", 2);
        edges[285] = new Edge(Colindale, HendonCentral, "Northern", 3);
        edges[286] = new Edge(HendonCentral, BrentCross, "Northern", 2);
        edges[287] = new Edge(BrentCross, GoldersGreen, "Northern", 3);
        edges[288] = new Edge(GoldersGreen, Hampstead, "Northern", 4);
        edges[289] = new Edge(Hampstead, BelsizePark, "Northern", 3);
        edges[290] = new Edge(BelsizePark, ChalkFarm, "Northern", 2);
        edges[291] = new Edge(ChalkFarm, CamdenTown, "Northern", 1);
        edges[292] = new Edge(CamdenTown, MorningtonCrescent, "Northern", 2);
        edges[293] = new Edge(MorningtonCrescent, Euston, "Northern", 2);
        edges[294] = new Edge(Euston, WarrenStreet, "Northern", 1);
        edges[295] = new Edge(WarrenStreet, GoodgeStreet, "Northern", 2);
        edges[296] = new Edge(GoodgeStreet, TottenhamCourtRoad, "Northern", 1);
        edges[297] = new Edge(TottenhamCourtRoad, LeicesterSquare, "Northern", 2);
        edges[298] = new Edge(LeicesterSquare, CharingCross, "Northern", 1);
        edges[299] = new Edge(CharingCross, Embankment, "Northern", 1);
        edges[300] = new Edge(Embankment, Waterloo, "Northern", 2);
        edges[301] = new Edge(Waterloo, Kennington, "Northern", 3);
        edges[302] = new Edge(CamdenTown, Euston, "Northern", 4);
        edges[303] = new Edge(Euston, KingsCrossStPancras, "Northern", 2);
        edges[304] = new Edge(KingsCrossStPancras, Angel, "Northern", 3);
        edges[305] = new Edge(Angel, OldStreet, "Northern",3);
        edges[306] = new Edge(OldStreet, Moorgate, "Northern", 2);
        edges[307] = new Edge(Moorgate, Bank, "Northern", 2);
        edges[308] = new Edge(Bank, LondonBridge, "Northern", 2);
        edges[309] = new Edge(LondonBridge, Borough, "Northern", 2);
        edges[310] = new Edge(Borough, ElephantAndCastle, "Northern", 2);
        edges[311] = new Edge(ElephantAndCastle, Kennington, "Northern", 2);
        edges[312] = new Edge(Kennington, Oval, "Northern", 3);
        edges[313] = new Edge(Oval, Stockwell, "Northern", 2);
        edges[314] = new Edge(Stockwell, ClaphamNorth, "Northern", 2);
        edges[315] = new Edge(ClaphamNorth, ClaphamCommon, "Northern", 2);
        edges[316] = new Edge(ClaphamCommon, ClaphamSouth, "Northern", 2);
        edges[317] = new Edge(ClaphamSouth, Balham, "Northern", 2);
        edges[318] = new Edge(Balham, TootingBec, "Northern", 2);
        edges[319] = new Edge(TootingBec, TootingBroadway, "Northern", 2);
        edges[320] = new Edge(TootingBroadway, ColliersWood, "Northern", 2);
        edges[321] = new Edge(ColliersWood, SouthWimbledon, "Northern", 2);
        edges[322] = new Edge(SouthWimbledon, Morden, "Northern", 3);
        
        //Piccadilly
        edges[323] = new Edge(Cockfosters, Oakwood, "Piccadilly", 2);
        edges[324] = new Edge(Oakwood, Southgate, "Piccadilly", 3);
        edges[325] = new Edge(Southgate, ArnosGrove, "Piccadilly", 4);
        edges[326] = new Edge(ArnosGrove, BoundsGreen, "Piccadilly", 2);
        edges[327] = new Edge(BoundsGreen, WoodGreen, "Piccadilly", 3);
        edges[328] = new Edge(WoodGreen, TurnpikeLane, "Piccadilly", 2);
        edges[329] = new Edge(TurnpikeLane, ManorHouse, "Piccadilly", 4);
        edges[330] = new Edge(ManorHouse, FinsburyParkVictoria, "Piccadilly", 2);
        edges[331] = new Edge(FinsburyParkVictoria, Arsenal, "Piccadilly", 1);
        edges[332] = new Edge(Arsenal, HollowayRoad, "Piccadilly", 2);
        edges[333] = new Edge(HollowayRoad, CaledonianRoad, "Piccadilly", 2);
        edges[334] = new Edge(CaledonianRoad, KingsCrossStPancras, "Piccadilly", 4);
        edges[335] = new Edge(KingsCrossStPancras, RussellSquare, "Piccadilly", 2);
        edges[336] = new Edge(RussellSquare, HolbornCentral, "Piccadilly", 2);
        edges[337] = new Edge(HolbornCentral, CoventGarden, "Piccadilly", 2);
        edges[338] = new Edge(CoventGarden, LeicesterSquare, "Piccadilly", 1);
        edges[339] = new Edge(LeicesterSquare, PiccadillyCircus, "Piccadilly", 1);
        edges[340] = new Edge(PiccadillyCircus, GreenPark, "Piccadilly", 2);
        edges[341] = new Edge(GreenPark, HydeParkCorner, "Piccadilly", 2);
        edges[342] = new Edge(HydeParkCorner, Knightsbridge, "Piccadilly", 2);
        edges[343] = new Edge(Knightsbridge, SouthKensington, "Piccadilly", 2);
        edges[344] = new Edge(SouthKensington, GloucesterRoad, "Piccadilly", 2);
        edges[345] = new Edge(GloucesterRoad, EarlsCourt, "Piccadilly", 2);
        edges[346] = new Edge(EarlsCourt, BaronsCourt, "Piccadilly", 3);
        edges[347] = new Edge(BaronsCourt, Hammersmith, "Piccadilly", 3);
        edges[348] = new Edge(Hammersmith, ActonTown, "Piccadilly", 8);
        edges[349] = new Edge(Hammersmith, TurnhamGreen, "Piccadilly", 4);
        edges[350] = new Edge(TurnhamGreen, ActonTown, "Piccadilly", 4);
        edges[351] = new Edge(ActonTown, SouthEaling, "Piccadilly", 3);
        edges[352] = new Edge(SouthEaling, Northfields, "Piccadilly", 1);
        edges[353] = new Edge(Northfields, BostonManor, "Piccadilly", 2);
        edges[354] = new Edge(BostonManor, Osterley, "Piccadilly", 3);
        edges[355] = new Edge(Osterley, HounslowEast, "Piccadilly", 2);
        edges[356] = new Edge(HounslowEast, HounslowCentral, "Piccadilly", 1);
        edges[357] = new Edge(HounslowCentral, HounslowWest, "Piccadilly", 3);
        edges[358] = new Edge(HounslowWest, HattonCross, "Piccadilly", 4);
        edges[359] = new Edge(HattonCross, HeathrowTerminal23, "Piccadilly", 4);
        edges[360] = new Edge(HeathrowTerminal23, HeathrowTerminal5, "Piccadilly", 4);
        edges[361] = new Edge(HattonCross, HeathrowTerminal4, "Piccadilly", 3);
        edges[362] = new Edge(ActonTown, EalingCommon, "Piccadilly", 2);
        edges[363] = new Edge(EalingCommon, NorthEaling, "Piccadilly", 2);
        edges[364] = new Edge(NorthEaling, ParkRoyal, "Piccadilly", 2);
        edges[365] = new Edge(ParkRoyal, Alperton, "Piccadilly", 2);
        edges[366] = new Edge(Alperton, SudburyTown, "Piccadilly", 3);
        edges[367] = new Edge(SudburyTown, SudburyHill, "Piccadilly", 2);
        edges[368] = new Edge(SudburyHill, SouthHarrow, "Piccadilly", 3);
        edges[369] = new Edge(SouthHarrow, RaynersLane, "Piccadilly", 5);
        edges[370] = new Edge(RaynersLane, Eastcote, "Piccadilly", 2);
        edges[371] = new Edge(Eastcote, RuislipManor, "Piccadilly", 4);
        edges[372] = new Edge(RuislipManor, Ruislip, "Piccadilly", 2);
        edges[373] = new Edge(Ruislip, Ickenham, "Piccadilly", 4);
        edges[374] = new Edge(Ickenham, Hillingdon, "Piccadilly", 2);
        edges[375] = new Edge(Hillingdon, Uxbridge, "Piccadilly", 4);
       
        
        //loops through all entries in "edge" and adds them to graph
        for (Edge e : edges) {
            graph.addEdge(e.getOne(), e.getTwo(), e.getLine(), e.getTime());
        }
        
        
        return graph;
    }
    
    public static void main(String[] args) {

//==========THIS BLOCK MAKES THE PROGRAM TO USE OS APPEARANCE============
//==========THIS BLOCK MAKES THE PROGRAM TO USE OS APPEARANCE============    
        try {
            // Set System L&F
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    //UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
//==========THIS BLOCK MAKES THE PROGRAM TO USE OS APPEARANCE============
//==========THIS BLOCK MAKES THE PROGRAM TO USE OS APPEARANCE============
        
        Graph graph = buildGraph();
        new Gui(graph);

    }

    
    @Override
    public void actionPerformed(ActionEvent e) { // butons' handler block
        if (e.getSource() == exit) { // if 'exit' is pressed...
            System.exit(0);  // ..terminates the application
        } else if (e.getSource() == route) { // if 'route' is pressed..
            String st1 = (String)station1.getSelectedItem(); // ..collects inputs(stations)
            Vertex stt1 = new Vertex(st1);
            String st2 = (String)station2.getSelectedItem();
            Vertex stt2 = new Vertex(st2);
            String v = (String) Gui.hours.getSelectedItem();// ..collects inputs(time)
            int read = Integer.parseInt(v); // parses the time input into integer.
            if (!graph.containsVertex(stt1)) {// if graph doesnt contain 'stt1' vertex...
                information.setText("<font face = \"Verdana\" size = \"4\">Station 1 not found!</font>"); // Destination            
            } else if (!graph.containsVertex(stt2)) { // if graph doesnt contain 'stt2' vertex...
                information.setText("<font face = \"Verdana\" size = \"4\">Station 2 not found!</font>"); // Destination            
            } else if (st1.equals(st2)) { // if both inputs are equal, displays error.
                information.setText("<font face = \"Verdana\" size = \"4\">Departure and destination station must be different.</font>");
            } else if (read >= 0 && read <= 4) { // checks the user entered time.
                information.setText("<font face = \"Verdana\" size = \"4\">Tube only runs from 05:00 to 23:59.</font>");
            } else if (graph.containsVertex(stt1) || graph.containsVertex(stt2)) { // if user inputs differ and exist, runs the algorithm.
                RouteFinder routerFinder = new RouteFinder(graph, st1); // runs the routefinder class and passes graph and st1 as initial vertex into the function.
                information.setText("");
                
                Path path = routerFinder.getPathTo(st2); // runds a function within
                //routeFinder class that finds a path from
                // initial vertex st1 to destination vertex st2
                
                String txt1 = "<b>/// STATION -- LINE -- TIME TO NEXT STOP ///</b><br />----------------------------------------------------------";
                String txt2 = path + "<br />----------------------------------------------------------";
                
                information.setText("<font face = \"Verdana\" size = \"4\">" + txt1 +'\n'+ txt2 + "</face>");

            }
        }
    }
}