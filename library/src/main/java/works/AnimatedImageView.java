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
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mobilesolutionworks.android.widget.R;

/**
 * Created by yunarta on 25/8/14.
 */
@TargetApi(Build.VERSION_CODES.FROYO)
public class AnimatedImageView extends ImageView {

    private Animation mAnimation;

    public AnimatedImageView(Context context) {
        super(context);
        init(context, null);
    }

    public AnimatedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public AnimatedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AnimatedImageView);
        if (ta != null) {
            int n = ta.getIndexCount();

            for (int i = 0; i < n; i++) {
                int attr = ta.getIndex(i);
                switch (attr) {
                    case R.styleable.AnimatedImageView_worksAnim: {
                        int id = ta.getResourceId(attr, -1);
                        if (id != -1) {
                            mAnimation = AnimationUtils.loadAnimation(context, id);
                        }
                        break;
                    }
                }
            }
            ta.recycle();
        }
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
}

