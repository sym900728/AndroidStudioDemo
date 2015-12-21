package com.sym.demolockpattern.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.List;


/**
 * indicator
 * @author xiaoming
 */
public class LockPatternIndicator extends View {
	
	private int width, height;
	private int radius;
	private Paint defaultPaint, selectPaint;
	private IndicatorCell[][] mIndicatorCells = new IndicatorCell[3][3];
	
	private static final String TAG = "LockPatternIndicator";

	public LockPatternIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	public LockPatternIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}
	
	private void init(Context context, AttributeSet attrs){
		initViewSize(context, attrs);
		initRadius();
		initPaint();
		init9IndicatorCells();
	}
	
	private void initViewSize(Context context, AttributeSet attrs){
		for(int i = 0; i < attrs.getAttributeCount(); i ++){
			String name = attrs.getAttributeName(i);
			if("layout_width".equals(name)){
				String value = attrs.getAttributeValue(i);
				this.width = LockPatternView.LockPatternTool.changeSize(context, value);
				//Log.e(TAG, "layout_width:" + value);
			}
			if("layout_height".equals(attrs.getAttributeName(i))){
				String value = attrs.getAttributeValue(i);
				this.height = LockPatternView.LockPatternTool.changeSize(context, value);
				//Log.e(TAG, "layout_height:" + value);
			}
		}
	}
	
	private void initRadius() {
		this.radius  = this.width/3/2 - this.width/3/3/2;
	}
	
	private void initPaint() {
		defaultPaint = new Paint();
		defaultPaint.setColor(0xFFB2B2B2);
		defaultPaint.setStrokeWidth(3.0f);
		defaultPaint.setStyle(Style.STROKE);
		defaultPaint.setAntiAlias(true);
		
		selectPaint = new Paint();
		selectPaint.setColor(0xFF01AAEE);
		selectPaint.setStrokeWidth(3.0f);
		selectPaint.setStyle(Style.FILL);
		selectPaint.setAntiAlias(true);
	}
	
	/**
	 * initialize nine cells
	 */
	private void init9IndicatorCells(){
		
		mIndicatorCells[0][0] = new IndicatorCell(width/3*0 +  width/3/2, height/3*0 + height/3/2, 1);
		mIndicatorCells[0][1] = new IndicatorCell(width/3*1 +  width/3/2, height/3*0 + height/3/2, 2);
		mIndicatorCells[0][2] = new IndicatorCell(width/3*2 +  width/3/2, height/3*0 + height/3/2, 3);
		
		mIndicatorCells[1][0] = new IndicatorCell(width/3*0 +  width/3/2, height/3*1 + height/3/2, 4);
		mIndicatorCells[1][1] = new IndicatorCell(width/3*1 +  width/3/2, height/3*1 + height/3/2, 5);
		mIndicatorCells[1][2] = new IndicatorCell(width/3*2 +  width/3/2, height/3*1 + height/3/2, 6);
		
		mIndicatorCells[2][0] = new IndicatorCell(width/3*0 +  width/3/2, height/3*2 + height/3/2, 7);
		mIndicatorCells[2][1] = new IndicatorCell(width/3*1 +  width/3/2, height/3*2 + height/3/2, 8);
		mIndicatorCells[2][2] = new IndicatorCell(width/3*2 +  width/3/2, height/3*2 + height/3/2, 9);
	}
	
	public void setIndicator(List<LockPatternView.Cell> cells) {
		for(LockPatternView.Cell cell : cells) {
			for(int i = 0; i < mIndicatorCells.length; i++) {
				for(int j = 0; j < mIndicatorCells[i].length; j++) {
					if (cell.getIndex() == mIndicatorCells[i][j].getIndex()) {
						Log.e(TAG, String.valueOf(cell.getIndex()));
						mIndicatorCells[i][j].setStatus(IndicatorCell.STATE_CHECK);
					}
				}
			}
		}
		this.postInvalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawToCanvas(canvas);
	}
	
	private void drawToCanvas(Canvas canvas) {
		for(int i = 0; i < mIndicatorCells.length; i++) {
			for(int j = 0; j < mIndicatorCells[i].length; j++) {
				if(mIndicatorCells[i][j].getStatus() == IndicatorCell.STATE_NORMAL) {
					canvas.drawCircle(mIndicatorCells[i][j].getX(), mIndicatorCells[i][j].getY(), radius, defaultPaint);
				} else if(mIndicatorCells[i][j].getStatus() == IndicatorCell.STATE_CHECK) {
					canvas.drawCircle(mIndicatorCells[i][j].getX(), mIndicatorCells[i][j].getY(), radius, selectPaint);
				}
			}
		}
	}
	
	public class IndicatorCell {
		private float x;
		private float y;
		private int status = 0;//default
		private int index;
		
		public static final int STATE_NORMAL = 0;
		public static final int STATE_CHECK = 1;
		
		public IndicatorCell(){}
		
		public IndicatorCell(float x, float y, int index){
			this.x = x;
			this.y = y;
			this.index = index;
		}
		
		public float getX(){
			return this.x;
		}
		
		public float getY(){
			return this.y;
		}
		
		public int getStatus(){
			return this.status;
		}
		
		public void setStatus(int status){
			this.status = status;
		}
		
		public int getIndex() {
			return this.index;
		}
		
		public void setIndex(int index) {
			this.index = index;
		}
	}
	
}
