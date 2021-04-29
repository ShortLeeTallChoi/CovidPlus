// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

var colorList = [ 'rgb(255, 102, 102)', 'rgb(255, 178, 102)',
		'rgb(255, 255, 102)', 'rgb(178, 255, 102)', 'rgb(102, 255, 102)',
		'rgb(102, 255, 178)', 'rgb(102, 255, 255)', 'rgb(102, 178, 255)',
		'rgb(102, 102, 255)', 'rgb(178, 102, 255)', 'rgb(255, 102, 255)',
		'rgb(255, 102, 178)', 'rgb(192, 192, 192)' ]

let chart;
function createSiDoChart(data) {
	var dataArr = new Array();
	for ( var value in data.sidoCase) {
		var gubun = data.sidoCase[value].gubun;
		if(gubun!='검역' && gubun!='합계'){
			dataArr.push({
				"category" : data.sidoCase[value].gubun,
				"value" : data.sidoCase[value].inc_dec
			});
		}
	}
	chart = am4core.createFromConfig({
		"type" : "XYChart",
		"data" : dataArr,
		"xAxes" : [ {
			"type" : "CategoryAxis",
			"dataFields" : {
				"category" : "category"
			},
			"renderer" : {
				"grid" : {
					"disabled" : true
				},
				"minGridDistance" : 20
			},
			"fontSize" : "12px"
		} ],
		"yAxes" : [ {
			"type" : "ValueAxis",
			"min" : 0,
			"renderer" : {
				"maxLabelPosition" : 0.98
			}
		} ],
		"series" : [ {
			"type" : "ColumnSeries",
			"columns" : {
				"template" : {
					"type" : "Column",
					"strokeOpacity" : 0
				},
			},
			"dataFields" : {
				"valueY" : "value",
				"categoryX" : "category"
			},
			"sequencedInterpolation" : true,
			"sequencedInterpolationDelay" : 100,
			"bullets" : [ {
				"type" : "LabelBullet",
				"label" : {
					"text" : "{value}",
					"truncate" : false,
					"hideOversized" : false,
					"fontSize" : "12px",
					"paddingBottom" : 10
				}
			} ],
			"heatRules" : [ {
				"target" : "columns.template",
				"property" : "fill",
				"min" : "#F5DBCB",
				"max" : "#ED7B84",
				"dataField" : "valueY"
			} ]
		} ]
	}, document.getElementById('amBarChart'));
}

function createSiDoTable(data){
	var htmlSource='';
	for ( var value in data.sidoCase) {
		var gubun = data.sidoCase[value].gubun;
		var inc_dec =  data.sidoCase[value].inc_dec;
		var def_cnt =  data.sidoCase[value].def_cnt;
		var inc_dec_distance =  data.sidoCase[value].inc_dec_distance;
		var def_cnt_distance =  data.sidoCase[value].def_cnt_distance;
		var tdSource = '';
		tdSource = addtd(tdSource, gubun);
		tdSource = addtd(tdSource, inc_dec.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")+distance_value(inc_dec_distance));
		tdSource = addtd(tdSource, def_cnt.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")+distance_value(def_cnt_distance));
		htmlSource = addtr(htmlSource,tdSource);
	}
	$('#sidoBody').html(htmlSource);
}

function distance_value(value){
	var html = "<span class='small_font'>(<span class='"
	if(value > 0){
		html = html+"small_red'>+"+value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
	}else if(value < 0){
		html = html+"small_blue'>"+value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
	}else{
		html = html+"small_black'>"+value.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",")
	}
	html = html+"</span>)</span>";
	return html;
}

function createAgeChart(data) {
	var dataArr = new Array();
	for ( var value in data.genAgeCase) {
		var gubun = data.genAgeCase[value].gubun;
		if(gubun != '남성' && gubun != '여성'){
			dataArr.push({
				"category" : data.genAgeCase[value].gubun,
				"value" : data.genAgeCase[value].inc_dec
			});
		}
	}
	let chart = am4core.createFromConfig({
		"type" : "PieChart",
		"data" : dataArr,
		"series" : [ {
			"type" : "PieSeries",
			"alignLabels" : false,
			"dataFields" : {
				"value" : "value",
				"category" : "category"
			},
			"ticks" : {
				"disabled" : true
			},
			"labels" : {
				"text" : "{category}\n{value.percent.formatNumber('#.0')}%",
				"radius" : "-40%",
				"adapter" : {
					"radius" : function(radius, target) {
						if (target.dataItem
								&& (target.dataItem.values.value.percent < 10)) {
							return 0;
						}
						return radius;
					}
				},
				"fill" : "white",
				"fontSize" : "12px"
			},
			"legendSettings" : {
				"labelText" : "{category} : {value}명"
			}
		} ],
		"legend" : {
			"type" : "Legend",
			"id" : "value",
			"setStateOnChildren" : false,
			"showOnInit" : false,
			"markers" : {
				"width" : 12,
				"height" : 12,
				"children" : [ {
					"cornerRadiusTopLeft" : 12,
					"cornerRadiusTopRight" : 12,
					"cornerRadiusBottomRight" : 12,
					"cornerRadiusBottomLeft" : 12,
					"strokeWidth" : 2,
					"strokeOpacity" : 1,
					"stroke" : "#ccc"
				} ]
			},
			"position" : "right",
			"maxWidth" : 160,
			"fontSize" : "12px",
			"useDefaultMarker" : true,
			"itemContainers" : {
				"paddingTop" : 3,
				"paddingBottom" : 3
			}
		},
		"radius" : "90%"
	}, document.getElementById('agePieChart'));
}

function createGenderChart(data) {
	var dataArr = new Array();
	for ( var value in data.genAgeCase) {
		var gubun = data.genAgeCase[value].gubun;
		if(gubun == '남성' || gubun == '여성'){
			dataArr.push({
				"category" : data.genAgeCase[value].gubun,
				"value" : data.genAgeCase[value].inc_dec
			});
		}
	}
	let chart = am4core.createFromConfig({
		"type" : "PieChart",
		"data" : dataArr,
		"series" : [ {
			"type" : "PieSeries",
			"alignLabels" : false,
			"dataFields" : {
				"value" : "value",
				"category" : "category"
			},
			"ticks" : {
				"disabled" : true
			},
			"labels" : {
				"text" : "{category}\n{value.percent.formatNumber('#.0')}%",
				"radius" : "-40%",
				"adapter" : {
					"radius" : function(radius, target) {
						if (target.dataItem
								&& (target.dataItem.values.value.percent < 10)) {
							return 0;
						}
						return radius;
					}
				},
				"fill" : "white",
				"fontSize" : "12px"
			},
			"legendSettings" : {
				"labelText" : "{category} : {value}명"
			}
		} ],
		"legend" : {
			"type" : "Legend",
			"id" : "value",
			"setStateOnChildren" : false,
			"showOnInit" : false,
			"markers" : {
				"width" : 12,
				"height" : 12,
				"children" : [ {
					"cornerRadiusTopLeft" : 12,
					"cornerRadiusTopRight" : 12,
					"cornerRadiusBottomRight" : 12,
					"cornerRadiusBottomLeft" : 12,
					"strokeWidth" : 2,
					"strokeOpacity" : 1,
					"stroke" : "#ccc"
				} ]
			},
			"position" : "right",
			"maxWidth" : 160,
			"fontSize" : "12px",
			"useDefaultMarker" : true,
			"itemContainers" : {
				"paddingTop" : 3,
				"paddingBottom" : 3
			}
		},
		"radius" : "90%"
	}, document.getElementById('genderPieChart'));
}

function addtr(htmlSource, tdSource){
	htmlSource = htmlSource+"<tr>\n";
	htmlSource = htmlSource+tdSource;
	htmlSource = htmlSource+"</tr>\n";
	return htmlSource;
}

function addtd(htmlSource, Source){
	htmlSource = htmlSource+"\t<td>";
	htmlSource = htmlSource+Source;
	htmlSource = htmlSource+"</td>\n";
	return htmlSource;
}


function createTotalLineChart(data){
	am4core.useTheme(am4themes_animated);
	let chart = am4core.create("totalLineChart", am4charts.XYChart);
	var dataArr = new Array();
	for ( var value in data.totalCaseWeek) {
		dataArr.push({
			"date" : data.totalCaseWeek[value].create_dt,
			  "income": data.totalCaseWeek[value].inc_dec,
			  "expenses": data.totalCaseWeek[value].inc_dec
		});
	}
	
	

	/* Create axes */
	let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "date";
	categoryAxis.renderer.minGridDistance = 30;
	categoryAxis.fontSize = "12px";
	
	/* Create value axis */
	let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

	/* Create series */
	let columnSeries = chart.series.push(new am4charts.ColumnSeries());
	columnSeries.name = "Income";
	columnSeries.dataFields.valueY = "income";
	columnSeries.dataFields.categoryX = "date";

	columnSeries.columns.template.tooltipText = "[#fff font-size: 15px]{categoryX}\n{valueY}명"
	columnSeries.columns.template.propertyFields.fillOpacity = "fillOpacity";
	columnSeries.columns.template.propertyFields.stroke = "stroke";
	columnSeries.columns.template.propertyFields.strokeWidth = "strokeWidth";
	columnSeries.columns.template.propertyFields.strokeDasharray = "columnDash";
	columnSeries.tooltip.label.textAlign = "middle";

	let lineSeries = chart.series.push(new am4charts.LineSeries());
	lineSeries.name = "Expenses";
	lineSeries.dataFields.valueY = "expenses";
	lineSeries.dataFields.categoryX = "date";

	lineSeries.stroke = am4core.color("#fdd400");
	lineSeries.strokeWidth = 3;
	lineSeries.propertyFields.strokeDasharray = "lineDash";
	lineSeries.tooltip.label.textAlign = "middle";

	let bullet = lineSeries.bullets.push(new am4charts.Bullet());
	bullet.fill = am4core.color("#fdd400"); // tooltips grab fill from parent by default
	bullet.tooltipText = "[#fff font-size: 15px]{categoryX}\n{valueY}명"
	let circle = bullet.createChild(am4core.Circle);
	circle.radius = 4;
	circle.fill = am4core.color("#fff");
	circle.strokeWidth = 3;

	chart.data = dataArr;
}