# How to/Get Started:

## Get started:

Import JAR file, create Main.class and another .class file of your naming. We will call this “EditScene” for this guide. This class will extend GameEditor.

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

}