/*
 * Copyright 2014-present Yunarta
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package works;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

/**
 * Created by yunarta on 25/8/14.
 */
@TargetApi(Build.VERSION_CODES.FROYO)
public class RotatingImageView extends ImageView {

    private RotateAnimation mAnimation;

    public RotatingImageView(Context context) {
        super(context);
        init();
    }

    public RotatingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotatingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mAnimation.setDuration(2 * getResources().getInteger(android.R.integer.config_longAnimTime));
        mAnimation.setInterpolator(new LinearInterpolator());
        mAnimation.setRepeatMode(Animation.RESTART);
        mAnimation.setRepeatCount(Animation.INFINITE);

        onVisibilityChanged(this, getVisibility());
    }


    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);

        switch (visibility) {
            case View.VISIBLE: {
                if (mAnimation != null) {
                    startAnimation(mAnimation);
                }
                break;
            }

            case View.INVISIBLE:
            case View.GONE: {
                if (mAnimation != null) {
                    mAnimation.cancel();
                }
                setAnimation(null);
                break;
            }
        }

    }

//    @Override
//    protected void onVisibilityChanged(View changedView, int visibility)
//    {
//        super.onVisibilityChanged(changedView, visibility);
//
//        if (changedView == this)
//        {
//        }
//    }

}

