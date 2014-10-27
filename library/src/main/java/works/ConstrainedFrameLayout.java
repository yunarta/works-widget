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

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.mobilesolutionworks.android.widget.R;

/**
 * Created by yunarta on 11/6/14.
 */

public class ConstrainedFrameLayout extends FrameLayout {

    protected static final int KEEP_WIDTH = 1;

    protected static final int KEEP_HEIGHT = 2;

    protected int ratioHorizontal = 1;

    protected int ratioVertical = 1;

    protected int keep;

    public ConstrainedFrameLayout(Context context) {
        this(context, null, 0);
    }

    public ConstrainedFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ConstrainedFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ConstrainedFrameLayout);
        if (ta != null) {
            int n = ta.getIndexCount();

            for (int i = 0; i < n; i++) {
                int attr = ta.getIndex(i);
                if (attr == R.styleable.ConstrainedFrameLayout_worksRatioHorizontal) {
                    ratioHorizontal = ta.getInt(attr, 1);
                } else if (attr == R.styleable.ConstrainedFrameLayout_worksRatioVertical) {
                    ratioVertical = ta.getInt(attr, 1);
                } else if (attr == R.styleable.ConstrainedFrameLayout_worksKeep) {
                    keep = ta.getInt(attr, 1);
                    if (keep > 3) {
                        keep = 1;
                    }
                }
            }
            ta.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        if (keep == KEEP_WIDTH) {
            height = width * ratioVertical / ratioHorizontal;
        } else {
            width = height * ratioHorizontal / ratioVertical;
        }

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(width & MEASURED_SIZE_MASK, height & MEASURED_SIZE_MASK);
    }
}
