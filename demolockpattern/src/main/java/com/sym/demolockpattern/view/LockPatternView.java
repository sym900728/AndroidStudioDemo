package com.sym.demolockpattern.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * nine block box lock
 * @author xiaoming
 */
public class LockPatternView extends View {
	
	private float movingX, movingY;
	private boolean isActionMove = false;
	private boolean isActionDown = false;
	private boolean isActionUp = true;
	private boolean isErrorShow = false;
	
	private long showErrorTime = 600L;
	private int width, height;
	private int cellRadius, cellInnerRadius;
	
	private Paint defaultPaint, selectPaint, errorPaint;
	
	private Cell[][] mCells = new Cell[3][3];
	private List<LockPatternView.Cell> sCells = new ArrayList<LockPatternView.Cell>();
	private OnPatternListener patterListener;
	
	private static final String TAG = "LockPatternView";

	public LockPatternView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}
	
	public LockPatternView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}
	
	private void init(Context context, AttributeSet attrs){
		initViewSize(context, attrs);
		initCellRadius();
		init9Cells();
		initPaints();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawToCanvas(canvas);
	}
	
	private void drawToCanvas(Canvas canvas){
		
		for(int i = 0; i< mCells.length; i++){
			for(int j = 0; j < mCells[i].length; j++){
				if(mCells[i][j].getStatus() == Cell.STATE_CHECK){
					selectPaint.setStyle(Style.STROKE);
					canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(), this.cellRadius, selectPaint);
					selectPaint.setStyle(Style.FILL);
					canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(), this.cellInnerRadius, selectPaint);
				} else if(mCells[i][j].getStatus() == Cell.STATE_NORMAL){
					canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(), this.cellRadius, defaultPaint);
				} else if(mCells[i][j].getStatus() == Cell.STATE_CHECK_ERROR){
					errorPaint.setStyle(Style.STROKE);
					canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(), this.cellRadius, errorPaint);
					errorPaint.setStyle(Style.FILL);
					canvas.drawCircle(mCells[i][j].getX(), mCells[i][j].getY(), this.cellInnerRadius, errorPaint);
				}
			}
		}
		
		if(sCells.size() > 0){
			
			Cell tempCell = sCells.get(0);
			
			for(int i = 1; i < sCells.size(); i++ ){
				Cell cell = sCells.get(i);
				if(cell.getStatus() == Cell.STATE_CHECK) {
					drawLine(tempCell, cell, canvas , selectPaint);
				} else if (cell.getStatus() == Cell.STATE_CHECK_ERROR){
					drawLine(tempCell, cell, canvas, errorPaint);
				}
				tempCell = cell;
			}
			
			if(isActionMove  && !isActionUp){
				canvas.drawLine(tempCell.getX(), tempCell.getY(), movingX, movingY, selectPaint);
			}
		}
		
	}
	
	private void initViewSize(Context context, AttributeSet attrs){
		for(int i = 0; i < attrs.getAttributeCount(); i ++){
			String name = attrs.getAttributeName(i);
			if("layout_width".equals(name)){
				String value = attrs.getAttributeValue(i);
				this.width = LockPatternTool.changeSize(context, value);
				//Log.e(TAG, "layout_width:" + value);
			}
			if("layout_height".equals(attrs.getAttributeName(i))){
				String value = attrs.getAttributeValue(i);
				this.height = LockPatternTool.changeSize(context, value);
				//Log.e(TAG, "layout_height:" + value);
			}
		}
	}
	
	/**
	 * initialize cell radius
	 */
	private void initCellRadius(){
		this.cellRadius = this.width/3/2 - this.width/3/3/2;
		this.cellInnerRadius = this.cellRadius/3;
	}
	
	/**
	 * initialize nine cells
	 */
	private void init9Cells(){
		
		mCells[0][0] = new Cell(width/3*0 +  width/3/2, height/3*0 + height/3/2, 1);
		mCells[0][1] = new Cell(width/3*1 +  width/3/2, height/3*0 + height/3/2, 2);
		mCells[0][2] = new Cell(width/3*2 +  width/3/2, height/3*0 + height/3/2, 3);
		
		mCells[1][0] = new Cell(width/3*0 +  width/3/2, height/3*1 + height/3/2, 4);
		mCells[1][1] = new Cell(width/3*1 +  width/3/2, height/3*1 + height/3/2, 5);
		mCells[1][2] = new Cell(width/3*2 +  width/3/2, height/3*1 + height/3/2, 6);
		
		mCells[2][0] = new Cell(width/3*0 +  width/3/2, height/3*2 + height/3/2, 7);
		mCells[2][1] = new Cell(width/3*1 +  width/3/2, height/3*2 + height/3/2, 8);
		mCells[2][2] = new Cell(width/3*2 +  width/3/2, height/3*2 + height/3/2, 9);
	}
	
	/**
	 * initialize paints
	 */
	private void initPaints(){
		defaultPaint = new Paint();
		defaultPaint.setColor(0xFF78D2F6);
		defaultPaint.setStrokeWidth(2.0f);
		defaultPaint.setStyle(Style.STROKE);
		defaultPaint.setAntiAlias(true);
		
		selectPaint = new Paint();
		selectPaint.setColor(0xFF00AAEE);
		selectPaint.setStrokeWidth(4.0f);
		//selectPaint.setStyle(Style.STROKE);
		selectPaint.setAntiAlias(true);
		
		errorPaint = new Paint();
		errorPaint.setColor(0xFFF3323B);
		errorPaint.setStrokeWidth(4.0f);
		//errorPaint.setStyle(Style.STROKE);
		errorPaint.setAntiAlias(true);
	}
	
	private void drawLine(Cell tempCell, Cell cell, Canvas canvas, Paint paint){
		canvas.drawLine(tempCell.getX(), tempCell.getY(), cell.getX(), cell.getY(), paint);
	}
	
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		float ex = event.getX();
		float ey = event.getY();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e(TAG, "ACTION_DOWN");
			
			isActionDown = true;
			if(isActionDown && isErrorShow){
				resumeDefault(sCells);
			}
			isActionUp = false;
			
			if(this.patterListener != null) {
				this.patterListener.onPatternStart();
			}
			
			Cell cell = checkSelectCell(ex, ey);
			if(cell != null) {
				Log.e(TAG, String.valueOf(cell.getIndex()));
				addSelectedCell(cell);
			}
			
			break;
		case MotionEvent.ACTION_MOVE:
			//Log.e(TAG, "ACTION_MOVE");
			
			isActionMove = true;
			movingX = ex;
			movingY = ey;
			cell = checkSelectCell(ex, ey);
			if(cell != null ){
				addSelectedCell(cell);
			}
			
			this.postInvalidate();
			break;
		case MotionEvent.ACTION_UP:
			Log.e(TAG, "ACTION_UP");
			
			isActionUp = true;
			isActionDown = false;
			
			if(this.patterListener != null) {
				this.patterListener.onPatternComplete(sCells);
			}
			
			//drawError(sCells);
			//resumeDefault(sCells);
			break;
		}
		return true;
	}
	
	public void setOnPatternListener(OnPatternListener patternListener){
		this.patterListener = patternListener;
	}
	
	/**
	 * resume default
	 * @param cells
	 */
	public void resumeDefault(List<Cell> cells) {
		isActionMove = false;
		for(Cell cell : cells) {
			cell.setStatus(Cell.STATE_NORMAL);
		}
		cells.clear();
		this.postInvalidate();
	}
	
	public void drawError(List<Cell> cells) {
		for(Cell cell : cells) {
			cell.setStatus(Cell.STATE_CHECK_ERROR);
		}
		this.postInvalidate();
		showErrorThread(cells);
	}
	
	private void showErrorThread(final List<Cell> cells){
		isErrorShow = true;
		new Thread(){
			public void run() {
				try {
					sleep(showErrorTime);
					if(!isActionDown){
						isErrorShow = false;
						resumeDefault(cells);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}
	
	/**
	 * check user's touch moving is or not in the area of cells 
	 * @param x
	 * @param y
	 * @return
	 */
	private Cell checkSelectCell(float x, float y) {
		for (int i = 0; i < mCells.length; i++) {
			for (int j = 0; j < mCells[i].length; j++) {
				Cell cell = mCells[i][j];
				if (LockPatternTool.checkInRound(cell.x, cell.y, 80, (int) x, (int) y)) {
					return cell;
				}
			}
		}
		return null;
	}
	
	/**
	 * add selected cell
	 * @param cell
	 */
	private void addSelectedCell(Cell cell){
		if(!sCells.contains(cell)){
			cell.setStatus(Cell.STATE_CHECK);
			sCells.add(cell);
		} 
		this.postInvalidate();
	}
	
	public class Cell {
		
		private float x;
		private float y;
		private int index;
		private int status = 0;//default
		
		public static final int STATE_NORMAL = 0;
		public static final int STATE_CHECK = 1;
		public static final int STATE_CHECK_ERROR = 2;
		
		public Cell(){}
		
		public Cell(float x, float y, int index){
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
		
		public int getIndex(){
			return this.index;
		}
		
		public int getStatus(){
			return this.status;
		}
		
		public void setStatus(int status){
			this.status = status;
		}
		
	}
	
	public static class LockPatternTool {
		
		/**
		 * change string value (ex: 10.0dip => 20) to int value
		 * @param context
		 * @param value
		 * @return
		 */
		public static int changeSize(Context context, String value){
			if(value.contains("dip")){
				float dip = Float.valueOf(value.substring(0, value.indexOf("dip")));
				return LockPatternTool.dip2px(context, dip);
			} else if(value.contains("px")){
				float px = Float.valueOf(value.substring(0, value.indexOf("px")));
				return (int)px;
			}
			return 0;
		}
		
		/**
		 * dip to px
		 * @param context
		 * @param dpValue
		 * @return
		 */
		public static int dip2px(Context context, float dpValue) {  
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (dpValue * scale + 0.5f);  
	    }
		
		/**
		 * check the touch cell is the circle
		 * @param sx
		 * @param sy
		 * @param r
		 * @param x
		 * @param y
		 * @return
		 */
		public static boolean checkInRound(float sx, float sy, float r, float x, float y) {
			return Math.sqrt((sx - x) * (sx - x) + (sy - y) * (sy - y)) < r;
		}
	}
	
	/**
	 * callback interface
	 */
	public static interface OnPatternListener {
		public void onPatternStart();
		public void onPatternComplete(List<Cell> cells);
	}
	

}
