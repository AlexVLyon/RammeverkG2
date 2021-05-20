# How to/Get Started:

## Get started:

<p>Import/create JAR file and import the file to your project.And create Main.class and another .class file of your naming. We Will Create a class named “EditScene” and Snake for this guide.</p>
<p> EditScene class inherits from GameEditor </p>
<p> We use The snake class functionality here in this way </p>

### Example 1:


    public class EditScene extends GameEditor {


       Snake snake;
       @Override
       public void init() {
          //Initialize code here
          snake = new Snake("filepath/image.png,200,100");
       }

       @Override
       public void Update()  {
         //Enter your game code functionality here.
         AddGameObjectToScreen(snake);
         AddTextToScreen("Hello World!", 200, 200);
         snake.MoveSnake();
       }
}


<p>Our Snake class inherits from Entity</p>

### Example 3:

    public class Snake extends Entity {

       public Snake(String filepath, int x, int y){
           super(filepath,x,y);         
           //Enter your snake code functionality here.
            
           public void MoveSnake(){
            ....
            ..
           }
            
    }

   

### Main method:
<p> We use methods from the screen class to create a screen, and then connect our EditScene class that we created to the ConnectClassToScreen() method and then we run the game </p>


           Screen s = new Screen();
           s.CreateWindow(1000, 1000, "title");
           s.ChangeScreenColor(1, 1, 1, 1); // white background
           GameEditor a = new EditScene();
           s.ConnectClassToScreen(a);
           s.run();
