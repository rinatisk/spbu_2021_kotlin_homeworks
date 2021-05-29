@file:Suppress("NoWildcardImports", "WildcardImport")

package homework6

import homework6.Sorter.sort
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

fun main() {
    Application(8)
}

class Application(private val numberOfThreads: Int = 4) {

     init {
        val renderer: XYItemRenderer = StandardXYItemRenderer(StandardXYItemRenderer.SHAPES_AND_LINES)
        val plot = CombinedDomainXYPlot(NumberAxis("Size"))
        plot.isRangePannable = true
        plot.add(XYPlot(
            generateData(), null, NumberAxis("Time"), renderer
        ))
        plot.orientation = PlotOrientation.VERTICAL
        val chart = JFreeChart(
            "Mt Merge Sort", JFreeChart.DEFAULT_TITLE_FONT, plot, true
        )
        val chartPanel = ChartPanel(chart)
        chartPanel.preferredSize = Dimension(1000, 600)
        val controlPanel = JPanel()
        controlPanel.add(JButton(UpdateAction(this, plot, 0)))
        for (i in 0 until numberOfThreads) {
            val jcb = JCheckBox(VisibleAction(renderer, i))
            jcb.isSelected = true
            renderer.setSeriesVisible(i, true)
            controlPanel.add(jcb)
        }
        val frame = JFrame("Mt Merge Sort")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(chartPanel, BorderLayout.CENTER)
        frame.add(controlPanel, BorderLayout.SOUTH)
        frame.pack()
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }

    fun generateData(): XYSeriesCollection {
        val data = XYSeriesCollection()
        for (i in 0 until numberOfThreads) {
            data.addSeries(generateSeries("Threads ${i + 1}", i + 1))
        }
        return data
    }

    private fun randomList(size: Int): MutableList<Int> = MutableList(size) { kotlin.random.Random.nextInt() }

    private fun generateSeries(key: String, thread: Int): XYSeries {
        val series = XYSeries(key)
            for (size in 1000..100000 step 10000) {
                val startTime = System.nanoTime()
                randomList(size).sort(thread)
                val endTime = System.nanoTime()
                series.add(size, (endTime - startTime) / 100_000)
            }

        return series
    }
}

private class UpdateAction(val app: Application, plot: CombinedDomainXYPlot, i: Int) : AbstractAction("Update plot ") {
    private val plot: XYPlot = plot.subplots[i] as XYPlot
    override fun actionPerformed(e: ActionEvent) {
        plot.dataset = app.generateData()
    }
}

private class VisibleAction(private val renderer: XYItemRenderer, private val i: Int) :
    AbstractAction("Threads ${i + 1}") {
    override fun actionPerformed(e: ActionEvent) {
        renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i))
    }
}
