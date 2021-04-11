package Components;

import org.easy2dGameEngine.Audio.Component;
import org.easy2dGameEngine.Audio.Texture;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {

    private Vector4f color;

    private Texture texture;


    @Override
    public void Start() {

    }
public SpriteRenderer(){}

    public Vector4f getColor() {
        return color;
    }



    public SpriteRenderer(Texture texture){
        this.texture = texture;
        this.color = new Vector4f(1,1,1,1);
    }

    public SpriteRenderer(Vector4f color){
        this.texture = null;
        this.color = color;
    }

    public Texture getTexture(){
        return  this.texture;
    }

    public Vector2f[] getTextCoordin(){
        Vector2f[] textCoordin = {
                new Vector2f(1,1),
                new Vector2f(1,0),
                new Vector2f(0,0),
                new Vector2f(0,1),
        };
        return textCoordin;
    }

}
