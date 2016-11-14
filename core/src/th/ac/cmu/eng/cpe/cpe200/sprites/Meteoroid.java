package th.ac.cmu.eng.cpe.cpe200.sprites;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import th.ac.cmu.eng.cpe.cpe200.Prefender;
import th.ac.cmu.eng.cpe.cpe200.bases.BaseMeteoroid;

import java.util.Random;
/**
 * Created by kawewut on 11/10/2016 AD.
 */
public class Meteoroid extends BaseMeteoroid {

    private AssetManager assetManager;
    private Texture meteoroidTexture;
    private boolean isHit = false;                  //เวลาชนโลก
    private boolean isDispose = false;              //เวลาโดนทำลานยแล้ว
    Random rand = new Random();
    public Meteoroid(AssetManager assetManager) {
        super(assetManager);
        randomPosition();
    }


    @Override
    public void loadAsset(AssetManager assetManager) {
        if (meteoroidTexture == null)
            meteoroidTexture = new Texture("badlogic.jpg");
    }

    @Override
    public void update(float deltaTime) {
        toCenter();
        getVelocityToCenter();
        getPosition().x += deltaTime * getVelocity().x;
        getPosition().y += deltaTime * getVelocity().y;

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(meteoroidTexture, getPosition().x - 60 / 2,
                getPosition().y - 60 / 2,
                60,60);
    }

    @Override
    public void dispose() {
        meteoroidTexture.dispose();
    }

    private void toCenter() {
        float x1 = Prefender.WIDTH / 2;
        float y1 = Prefender.HEIGHT / 2;
        float x2 = getPosition().x;
        float y2 = getPosition().y;
        float dimention = (float) Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
        if (dimention < 10) {
            isHit = true;
        }
    }

    public void randomPosition() {
        int b = rand.nextInt(4);
        int x = 0;
        int y = 0;
        int error = rand.nextInt(100);
        if (b == 0) {
            x = rand.nextInt(Prefender.WIDTH) + error;
        } else if (b == 1) {
            y = rand.nextInt(Prefender.HEIGHT) + error ;
        } else if (b == 2) {
            x = Prefender.WIDTH;
            y = rand.nextInt(Prefender.HEIGHT) + error;
        } else {
            x = rand.nextInt(Prefender.HEIGHT) + error;
            y = Prefender.HEIGHT;
        }
        getPosition().x = x;
        getPosition().y = y;
        velocity_scale = 40;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean kit) {
        isHit = kit;
    }
}