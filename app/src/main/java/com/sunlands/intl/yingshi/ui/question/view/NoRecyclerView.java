package com.sunlands.intl.yingshi.ui.question.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 创 建 人: xueh
 * 创建日期: 2019/2/24 15:38
 * 备注：可以让RecyclerView是否滚动
 */
public class NoRecyclerView extends RecyclerView {
    private boolean isScroll = true;
    private static final int INVALID_POINTER = -1;
    private int mScrollPointerId = INVALID_POINTER;
    private int mInitialTouchX, mInitialTouchY;
    private int mTouchSlop;

    public NoRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public NoRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NoRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    public void setScroll(boolean scroll) {
        isScroll = scroll;
    }

    private void init(Context context) {
        final ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledTouchSlop();
    }

    @Override
    public void setScrollingTouchSlop(int slopConstant) {
        super.setScrollingTouchSlop(slopConstant);
        final ViewConfiguration vc = ViewConfiguration.get(getContext());
        switch (slopConstant) {
            case TOUCH_SLOP_PAGING:
                mTouchSlop = vc.getScaledPagingTouchSlop();
                break;
            default:
            case TOUCH_SLOP_DEFAULT:
                mTouchSlop = vc.getScaledTouchSlop();
                break;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (isScroll) {
            final int action = e.getActionMasked();
            final int actionIndex = e.getActionIndex();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mScrollPointerId = e.getPointerId(0);
                    mInitialTouchX = (int) (e.getX() + 0.5f);
                    mInitialTouchY = (int) (e.getY() + 0.5f);
                    return super.onInterceptTouchEvent(e);

                case MotionEvent.ACTION_POINTER_DOWN:
                    mScrollPointerId = e.getPointerId(actionIndex);
                    mInitialTouchX = (int) (e.getX(actionIndex) + 0.5f);
                    mInitialTouchY = (int) (e.getY(actionIndex) + 0.5f);
                    return super.onInterceptTouchEvent(e);

                case MotionEvent.ACTION_MOVE: {
                    final int index = e.findPointerIndex(mScrollPointerId);
                    if (index < 0) {
                        return false;
                    }
                    final int x = (int) (e.getX(actionIndex) + 0.5f);
                    final int y = (int) (e.getY(actionIndex) + 0.5f);

                    LayoutManager layoutManager = getLayoutManager();

                    if (layoutManager != null && getScrollState() != SCROLL_STATE_DRAGGING) {
                        final int dx = x - mInitialTouchX;
                        final int dy = y - mInitialTouchY;
                        final boolean canScrollHorizontally = layoutManager.canScrollHorizontally();
                        final boolean canScrollVertically = layoutManager.canScrollVertically();
                        boolean startScroll = false;

                        if (canScrollHorizontally && Math.abs(dx) > mTouchSlop && (Math.abs(dx) * 0.5f
                                >= Math.abs(dy) || canScrollVertically)) {
                            startScroll = true;
                        }

                        if (canScrollVertically && Math.abs(dy) > mTouchSlop && (Math.abs(dy) * 0.5f
                                >= Math.abs(dx) || canScrollHorizontally)) {
                            startScroll = true;
                        }

                        return startScroll && super.onInterceptTouchEvent(e);
                    }
                    return super.onInterceptTouchEvent(e);
                }

                default:
                    return super.onInterceptTouchEvent(e);
            }
        } else {
            return false;
        }
    }
}
