package com.example.shikikancompanion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shikikancompanion.Database.DatabaseHandler;
import com.github.chrisbanes.photoview.PhotoView;

public class TacticalDisplay extends Activity {
    // Hold a reference to the current animator,
    // so that it can be canceled mid-way.
    private Animator mCurrentAnimator;

    final String white = "#FFFFFF";
    final String buffTile = "#00FFDE";

    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.
    private int mShortAnimationDuration;

    private indexHolder doll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tactical_display);
        init();

        //getting index stuff
        Intent getIndex = getIntent();
        int index = getIndex.getIntExtra("index", 1);
        DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        doll = db.getDollInfo(index);
        setUpViewPager(doll);

        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);

        displayInfo(doll);
    }

    public void setUpViewPager(indexHolder doll){
        String[] img = new String[]{doll.getPictureUrl(), doll.getPictureDUrl()};

        ViewPager vp = findViewById(R.id.imgViewPager);
        ViewPagerAdapter vpa = new ViewPagerAdapter(this, img);
        vp.setAdapter(vpa);
    }


    private void displayInfo(indexHolder doll) {

        //Setting up stat data
//        Left side
        TextView name, hp, firepower, evasion, accuracy, rof, tileD, skillN, skillC, skillD, critical, ap, speed, armor, clip;
        name = findViewById(R.id.name);
        hp = findViewById(R.id.health);
        firepower = findViewById(R.id.firepower);
        evasion = findViewById(R.id.evasion);
        accuracy = findViewById(R.id.accuracy);
        rof = findViewById(R.id.rof);

        name.setText(doll.getName());
        hp.setText(doll.getHp() + "");
        firepower.setText(doll.getFirepower() + "");
        evasion.setText(doll.getEvasion() + "");
        accuracy.setText(doll.getAccuracy() + "");
        rof.setText(doll.getRof() + "");

//        Right side
        critical = findViewById(R.id.critical);
        ap = findViewById(R.id.ap);
        speed = findViewById(R.id.speed);
        armor = findViewById(R.id.armor);
        clip = findViewById(R.id.clip);

        critical.setText(doll.getCritical()+"");
        ap.setText(doll.getAp()+"");
        speed.setText(doll.getSpeed()+"");
        armor.setText(doll.getArmor()+"");
        clip.setText(doll.getClip()+"");

        //Skill
        ImageView skillIcon = findViewById(R.id.skillP);
        Glide
                .with(getApplicationContext())
                .load(doll.getSkillUrl())
                .into(skillIcon);

        skillN = findViewById(R.id.skillN);
        skillC = findViewById(R.id.skillC);
        skillD = findViewById(R.id.skillD);

        skillN.setText(doll.getSkill());
        String cd = (doll.getSkillic() == 0 && doll.getSkillcd() == 0) ? "Passive" : doll.getSkillic() + "s initial cooldown, " + doll.getSkillcd()+"s cooldown";
        skillC.setText(cd);
        skillD.setText(doll.getSkilld());

        //Tile
        tileD = findViewById(R.id.tileDescription);
        tileD.setText(doll.getTiled());
        String[] tile = doll.getTile().split("");
        setTileBuffer(Integer.parseInt(tile[1]));

        for (int i = 2; i < tile.length; i++) {
            setTileBuff(Integer.parseInt(tile[i]));
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private Context context;
        private String[] imgUrl;

        private ViewPagerAdapter(Context context, String[] imgUrl) {
            this.context = context;
            this.imgUrl = imgUrl;
        }

        @Override
        public int getCount() {
            return imgUrl.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup c, final int position) {
            ImageView imgView = new ImageView(context);
            Glide
                    .with(context)
                    .load(imgUrl[position])
                    .into(imgView);
            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View con = findViewById(R.id.imgViewPager);
                    zoomImageFromThumb(con, imgUrl[position]);
                }
            });
            c.addView(imgView);
            return imgView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }

    private void init() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .85), (int) (height * .85));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }

    private void setTileBuffer(int buffer) {
        ImageView bufferSpot;
        switch (buffer) {
            case 7:
                bufferSpot = findViewById(R.id.topLeft);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 8:
                bufferSpot = findViewById(R.id.topCenter);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 9:
                bufferSpot = findViewById(R.id.topRight);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 4:
                bufferSpot = findViewById(R.id.middleLeft);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 5:
                bufferSpot = findViewById(R.id.middleCenter);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 6:
                bufferSpot = findViewById(R.id.middleRight);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 1:
                bufferSpot = findViewById(R.id.bottomLeft);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 2:
                bufferSpot = findViewById(R.id.bottomCenter);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
            case 3:
                bufferSpot = findViewById(R.id.bottomRight);
                bufferSpot.setBackgroundColor(Color.parseColor(white));
                break;
        }
    }
    private void setTileBuff(int buff) {
        ImageView buffSpot;
        switch (buff) {
            case 7:
                buffSpot = findViewById(R.id.topLeft);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 8:
                buffSpot = findViewById(R.id.topCenter);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 9:
                buffSpot = findViewById(R.id.topRight);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 4:
                buffSpot = findViewById(R.id.middleLeft);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 5:
                buffSpot = findViewById(R.id.middleCenter);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 6:
                buffSpot = findViewById(R.id.middleRight);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 1:
                buffSpot = findViewById(R.id.bottomLeft);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 2:
                buffSpot = findViewById(R.id.bottomCenter);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
            case 3:
                buffSpot = findViewById(R.id.bottomRight);
                buffSpot.setBackgroundColor(Color.parseColor(buffTile));
                break;
        }
    }

    private void zoomImageFromThumb(final View thumbView, String imgUrl) {
        // If there's an animation in progress, cancel it
        // immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final PhotoView expandedImageView = findViewById(
                R.id.expanded_image);

        final ScrollView info = findViewById(R.id.everythingElse);

        Glide
                .with(getApplicationContext())
                .load(imgUrl)
                .into(expandedImageView);

        // Calculate the starting and ending bounds for the zoomed-in image.
        // This step involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail,
        // and the final bounds are the global visible rectangle of the container
        // view. Also set the container view's offset as the origin for the
        // bounds, since that's the origin for the positioning animation
        // properties (X, Y).

        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final
        // bounds using the "center crop" technique. This prevents undesirable
        // stretching during the animation. Also calculate the start scaling
        // factor (the end scaling factor is always 1.0).

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumbnail and show the zoomed-in view. When the animation
        // begins, it will position the zoomed-in view in the place of the
        // thumbnail.
        thumbView.setAlpha(0f);
        info.setVisibility(View.INVISIBLE);
        expandedImageView.setVisibility(View.VISIBLE);

        // Set the pivot point for SCALE_X and SCALE_Y transformations
        // to the top-left corner of the zoomed-in view (the default
        // is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and
        // scale properties (X, Y, SCALE_X, and SCALE_Y).
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X,
                        startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X,
                        startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView,
                        View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down
        // to the original bounds and show the thumbnail instead of
        // the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel,
                // back to their original values.
                AnimatorSet set = new AnimatorSet();
                set.play(ObjectAnimator
                        .ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView,
                                        View.SCALE_Y, startScaleFinal));
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f);
                        info.setVisibility(View.VISIBLE);
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f);
                        info.setVisibility(View.VISIBLE);
                        expandedImageView.setVisibility(View.INVISIBLE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
