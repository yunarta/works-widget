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
                switch (attr) {
                    case R.styleable.ConstrainedFrameLayout_ratioHorizontal: {
                        ratioHorizontal = ta.getInt(attr, 1);
                        break;
                    }

                    case R.styleable.ConstrainedFrameLayout_ratioVertical: {
                        ratioVertical = ta.getInt(attr, 1);
                        break;
                    }

                    case R.styleable.ConstrainedFrameLayout_keep: {
                        keep = ta.getInt(attr, 1);
                        if (keep > 3) {
                            keep = 1;
                        }
                        break;
                    }
                }
            }
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
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }
}
