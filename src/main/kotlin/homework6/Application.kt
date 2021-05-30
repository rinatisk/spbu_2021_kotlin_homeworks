@file:Suppress("NoWildcardImports", "WildcardImport")

package homework6

import homework6.MergeSorter.mergeSort
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.CombinedDomainXYPlot
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.chart.renderer.xy.StandardXYItemRenderer
import org.jfree.chart.renderer.xy.XYItemRenderer
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.*
import kotlin.math.log2
import kotlin.math.pow

fun main() {
    Application(8, 1000, 50000)
}

class Application(private val numberOfThreads: Int = 4, private val stepNumber: Int, private val maxSize: Int) {

    init {
        val renderer: XYItemRenderer = StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES)
        val plot = generatePlot(renderer)
        val chartPanel = generateChartPanel(plot)
        val controlPanel = generateControlPanel(plot)
        generateJCheckBox(renderer, controlPanel)
        generateFrame("Mt Merge Sort", chartPanel, controlPanel)
    }

    private fun generatePlot(renderer: XYItemRenderer): CombinedDomainXYPlot {
        val plot = CombinedDomainXYPlot(NumberAxis("Size"))
        plot.isRangePannable = true
        plot.add(
            XYPlot(
                generateData(), null, NumberAxis("Time"), renderer
            )
        )
        plot.orientation = PlotOrientation.VERTICAL
        return plot
    }

    private fun generateData(): XYSeriesCollection {
        val data = XYSeriesCollection()
        var i = 1
        while (i <= numberOfThreads) {
            data.addSeries(generateSeries("Threads $i"))
            i *= 2
        }
        return data
    }

    private fun getRandomList(size: Int): MutableList<Int> = MutableList(size) { kotlin.random.Random.nextInt() }

    private fun generateFrame(title: String, chartPanel: ChartPanel, controlPanel: JPanel) {
        val frame = JFrame(title)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(chartPanel, BorderLayout.CENTER)
        frame.add(controlPanel, BorderLayout.SOUTH)
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }

    private fun generateJCheckBox(renderer: XYItemRenderer, controlPanel: JPanel) {
        var i = 1
        while (i <= numberOfThreads) {
            val jcb = JCheckBox(VisibleAction(renderer, log2(i.toDouble()).toInt()))
            jcb.isSelected = true
            renderer.setSeriesVisible(log2(i.toDouble()).toInt(), true)
            controlPanel.add(jcb)
            i *= 2
        }
    }

    private fun generateControlPanel(plot: CombinedDomainXYPlot): JPanel {
        val controlPanel = JPanel()
        controlPanel.add(JButton(UpdateAction(this, plot, 0)))
        return controlPanel
    }

    private fun generateChartPanel(plot: CombinedDomainXYPlot): ChartPanel {
        val chart = JFreeChart(
            "Mt Merge Sort", JFreeChart.DEFAULT_TITLE_FONT, plot, true
        )
        val chartPanel = ChartPanel(chart)
        chartPanel.preferredSize = Dimension(1000, 600)
        return chartPanel
    }

    private fun generateSeries(key: String): XYSeries {
        val series = XYSeries(key)
        for (size in 1000..maxSize step stepNumber) {
            val startTime = System.nanoTime()
            getRandomList(size).mergeSort(numberOfThreads)
            val endTime = System.nanoTime()
            series.add(size, (endTime - startTime))
        }

        return series
    }

    private class UpdateAction(
        val app: Application,
        plot: CombinedDomainXYPlot,
        i: Int
    ) : AbstractAction("Update plot ") {
        private val plot: XYPlot = plot.subplots[i] as XYPlot
        override fun actionPerformed(e: ActionEvent) {
            plot.dataset = app.generateData()
        }
    }

    private class VisibleAction(private val renderer: XYItemRenderer, private val i: Int) :
        AbstractAction("Threads ${2.0.pow(i).toInt()}") {
        override fun actionPerformed(e: ActionEvent) {
            renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i))
        }
    }
}
