package cn.cust.elec.utils;

import java.awt.Font;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

public class JfreechartUtil {
	public static JFreeChart createChart(DefaultPieDataset dataset,String theme){
		JFreeChart chart = ChartFactory.createPieChart3D(theme, // 图表标题
				dataset, // 数据集
                true, // 是否显示图例(对于简单的柱状图必须是 false)
                false, // 是否生成工具
                false // 是否生成 URL 链接
                );

        //中文乱码
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelFont(new Font("黑体", Font.PLAIN, 20));
        TextTitle textTitle = chart.getTitle();
        textTitle.setFont(new Font("黑体", Font.PLAIN, 20));
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12));
        return chart;
	}
	
	public static DefaultPieDataset getDataset(Map<String,Double> date){
		DefaultPieDataset dataset = new DefaultPieDataset();
		if(date!=null){
			for(Entry<String,Double> entry :date.entrySet()){
				dataset.setValue(entry.getKey(), entry.getValue());
			}
		}
		return dataset;
	}
}
 