package bcpa.backend;

import bcpa.database.DataRetrieval;


public class SeatingChart {
	
	private int chartId;
	private int showId;
	private int chartRows;
	private int chartColumns;

	/**
	 * 
	 */
	public SeatingChart(int show, int rows, int columns) {
		this.setShowId(show);
		this.setChartRows(rows);
		this.setChartColumns(columns);
	}
	
	public SeatingChart(int chart, int show, int rows, int columns) {
		this.setChartId(chart);
		this.setShowId(show);
		this.setChartRows(rows);
		this.setChartColumns(columns);
	}

	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

	/**
	 * @return the showId
	 */
	public int getShowId() {
		return showId;
	}

	/**
	 * @param showId the showId to set
	 */
	public void setShowId(int showId) {
		this.showId = showId;
	}

	/**
	 * @return the chartRows
	 */
	public int getChartRows() {
		return chartRows;
	}

	/**
	 * @param chartRows the chartRows to set
	 */
	public void setChartRows(int chartRows) {
		this.chartRows = chartRows;
	}

	/**
	 * @return the chartColumns
	 */
	public int getChartColumns() {
		return chartColumns;
	}

	/**
	 * @param chartColumns the chartColumns to set
	 */
	public void setChartColumns(int chartColumns) {
		this.chartColumns = chartColumns;
	}
	
	public static SeatingChart getSeatingChart(int show) {
		return DataRetrieval.getSeatingChartByShowId(show);
	}

}
