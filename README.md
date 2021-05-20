# How to/Get Started:

## Get started:

Import/create JAR file and import the file to your project. Create Main.class and another .class file of your naming. We will call this “EditScene” for this guide. This class will extend GameEditor.
Now you have two classes, main.java and EditScene.java:

public class EditScene extends GameEditor {
@Override
public void init() {
//Initialize code here
}

@Override
public void Update()  {
//Enter your game code functionality here.
}
}

public class Snake extends Entity {
You have to inherit the entity constructor, which has aString file, int x and int y. Make sure that the String is of type .png
public Snake(String filepath, int x, int y){
       super(filepath,x,y);
}
//Enter your snake code functionality here.

//Main File
 Screen s = new Screen();
        s.CreateWindow(1000, 1000, "title");
        s.ChangeScreenColor(1, 1, 1, 1); // white background
        GameEditor a = new EditScene();

        s.ConnectClassToScreen(a);
        s.run();
}