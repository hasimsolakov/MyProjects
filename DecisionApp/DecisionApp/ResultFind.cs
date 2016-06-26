using System;
using Excel = Microsoft.Office.Interop.Excel;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DecisionApp
{
    public class ResultFind
    {
        public static int rows = 0;
        public static int cols = 0;
        public static string columnName = null;
        public static decimal optimismCoef;
        public static decimal[] probabilities ;
        public static bool normal;
        public static List<string> alternatives = new List<string>();
        public static string[] columnNames = { "State 1", "State 2", "State 3", "State 4", "State 5", "State 6", "State 7","State 8","State 9" };
        //public static List<List<decimal>> ReadingTable()
        //{
        //    var xlApp = new Excel.Application();
        //    string misValue ="table.xls";
        //    var xlWorkbook = xlApp.Workbooks.Add(misValue);
        //    var xlWorkSheet = (Excel.Worksheet)xlWorkbook.Worksheets.get_Item(1);
        //    var range = xlWorkSheet.UsedRange;
        //    List<List<decimal>> filledTable = new List<List<decimal>>();
        //    for ( int i = 3; i <rows+3 ; i++)
        //    {
        //        List<decimal> row = new List<decimal>(cols);
        //        for (int k = 2; k < cols+2; k++)
        //        {   
        //           string input = (range.Cells[i, k] as Excel.Range).Text.ToString();
        //            row.Add(decimal.Parse(input));
        //        }
        //        filledTable.Add(row);
        //    }
        //    for (int i = 3; i < rows+3; i++)
        //    {
        //        alternatives.Add((xlWorkSheet.Cells[i, 1] as Excel.Range).Text.ToString());
        //    }
        //    probabilities = new decimal[cols];
        //    columnName = (xlWorkSheet.Cells[2, cols + 3] as Excel.Range).Text.ToString();
        //    optimismCoef = decimal.Parse((xlWorkSheet.Cells[1, cols + 3] as Excel.Range).Text.ToString());
        //    for (int i = 2, k = 0; i < cols+2; i++,k++)
        //    {
        //        probabilities[k] = decimal.Parse((xlWorkSheet.Cells[2, i] as Excel.Range).Text.ToString());
        //    }
        //    string type = (range.Cells[3, cols+3] as Excel.Range).Text.ToString();
        //    if (type=="Normal"||type=="normal")
        //    {
        //        normal = true;
        //    }
        //    else if( type=="Inverted"||type=="inverted")
        //    {
        //        normal = false;
        //    }

        //    return filledTable;
        //}
       public static void CreatingTable(List<string> resultstoPrint, decimal [,] table)
        {
            Excel.Application xlApp = new Excel.Application();
            

            if (xlApp == null)
            {
                Console.WriteLine("Excel is not properly installed!!");
                return;
            }
            object misValue =System.Reflection.Missing.Value;
            var xlWorkbook = xlApp.Workbooks.Add(misValue);
            var xlWorkSheet = (Excel.Worksheet)xlWorkbook.Worksheets.get_Item(1);
            
            for (int i = 2; i < cols+2; i++)
            {
                xlWorkSheet.Cells[1, i] = "State " + (i - 1);
                xlWorkSheet.Cells[2, i] = "Probability";
            }
            for (int i = 3, j = 0; i < rows+3; i++,j++)
            {
                for (int k = 2, l = 0 ; k < cols+2; k++,l++)
                {
                    xlWorkSheet.Cells[i, k] = table[j, l];
                }
            }
            xlWorkSheet.Cells[1, cols + 2] = "Optimism coefficient :";
            xlWorkSheet.Cells[2, cols + 2] = "State under certainty :";
            xlWorkSheet.Cells[3, cols + 2] = "Type of the matrix :";
            xlWorkSheet.Cells[1, cols + 3] = optimismCoef;
            xlWorkSheet.Cells[2, cols + 3] = columnName;
            xlWorkSheet.Cells[3, cols + 3] = normal ? "Normal":"Inverted";
            string certaintyResult = resultstoPrint[0];
            string riskResult = resultstoPrint[1];
            string waldResult = resultstoPrint[2];
            string optimisticResult = resultstoPrint[3];
            string hurwiczResult = resultstoPrint[4];
            string laplaceResult = resultstoPrint[5];
            string savageResult = resultstoPrint[6];
            (xlWorkSheet.Cells[1, cols + 5] as Excel.Range).Value = "Results";
            (xlWorkSheet.Cells[2, cols + 4] as Excel.Range).Value = "Under certainty";
            (xlWorkSheet.Cells[2, cols + 5] as Excel.Range).Value = certaintyResult;
            (xlWorkSheet.Cells[3, cols + 4] as Excel.Range).Value = "Under Risk";
            (xlWorkSheet.Cells[3, cols + 5] as Excel.Range).Value = riskResult;
            (xlWorkSheet.Cells[4, cols + 4] as Excel.Range).Value = "Wald Decision";
            (xlWorkSheet.Cells[4, cols + 5] as Excel.Range).Value = waldResult;
            (xlWorkSheet.Cells[5, cols + 4] as Excel.Range).Value = "Total Optimistic Decision";
            (xlWorkSheet.Cells[5, cols + 5] as Excel.Range).Value = optimisticResult;
            (xlWorkSheet.Cells[6, cols + 4] as Excel.Range).Value = "Hurwicz Decision";
            (xlWorkSheet.Cells[6, cols + 5] as Excel.Range).Value = hurwiczResult;
            (xlWorkSheet.Cells[7, cols + 4] as Excel.Range).Value = "Laplace Decision";
            (xlWorkSheet.Cells[7, cols + 5] as Excel.Range).Value = laplaceResult;
            (xlWorkSheet.Cells[8, cols + 4] as Excel.Range).Value = "Savage Decision";
            (xlWorkSheet.Cells[8, cols + 5] as Excel.Range).Value = savageResult;
            for (int i = 3,k=0; i < rows+3; i++,k++)
            {
                xlWorkSheet.Cells[i, 1] = alternatives[k];
            }
            xlWorkSheet.Columns.AutoFit();
            var formatRange = xlWorkSheet.UsedRange;
            AllBorders(formatRange);
            xlWorkbook.SaveAs("DecisionTable.xls", Excel.XlFileFormat.xlWorkbookNormal, misValue, misValue, misValue, misValue, Excel.XlSaveAsAccessMode.xlExclusive, misValue, misValue, misValue, misValue, misValue);
           // xlWorkbook.Close(true, misValue, misValue);
           // xlApp.Quit();
            releaseObject(xlWorkSheet);
            releaseObject(xlWorkbook);
            xlApp.Visible = true;
            releaseObject(xlApp);
        }

        static void AllBorders(Excel.Range formatRange)
        {
            formatRange.Borders.get_Item(Excel.XlBordersIndex.xlEdgeLeft).LineStyle = Excel.XlLineStyle.xlContinuous;
            formatRange.Borders.get_Item(Excel.XlBordersIndex.xlEdgeRight).LineStyle = Excel.XlLineStyle.xlContinuous;
            formatRange.Borders.get_Item(Excel.XlBordersIndex.xlInsideHorizontal).LineStyle = Excel.XlLineStyle.xlContinuous;
            formatRange.Borders.get_Item(Excel.XlBordersIndex.xlInsideVertical).LineStyle = Excel.XlLineStyle.xlContinuous;
            formatRange.BorderAround(Excel.XlLineStyle.xlContinuous, Excel.XlBorderWeight.xlThin, Excel.XlColorIndex.xlColorIndexAutomatic, Excel.XlColorIndex.xlColorIndexAutomatic);
        }

        static void releaseObject(object obj)
        {
            try
            {
                System.Runtime.InteropServices.Marshal.ReleaseComObject(obj);
                obj = null;
            }
            catch (Exception ex)
            {
                obj = null;
                Console.WriteLine("Exception Occured while releasing object " + ex.ToString());
            }
            finally
            {
                GC.Collect();
            }
        }
        public static decimal[,] SavageDecision(decimal[,] table, bool NormalOrNot)
        {
            decimal[,] newFormedTable = new decimal[rows, cols];
            if (NormalOrNot)
            {
                decimal[] colMaxes = new decimal[cols];
                for (int i = 0; i < cols; i++)
                {
                    decimal[] column = new decimal[rows];
                    for (int k = 0; k < rows; k++)
                    {
                        column[k] = table[k, i];
                    }
                    colMaxes[i] = column.Max();
                }
                for (int i = 0; i < cols; i++)
                {
                    decimal[] column = new decimal[rows];
                    for (int k = 0; k < rows; k++)
                    {
                        newFormedTable[k, i] = Math.Abs(table[k, i] - colMaxes[i]);
                    }
                }
            }
            else
            {
                decimal[] colMins = new decimal[cols];
                for (int i = 0; i < cols; i++)
                {
                    decimal[] column = new decimal[rows];
                    for (int k = 0; k < rows; k++)
                    {
                        column[k] = table[k, i];
                    }
                    colMins[i] = column.Min();
                }
                for (int i = 0; i < cols; i++)
                {
                    decimal[] column = new decimal[rows];
                    for (int k = 0; k < rows; k++)
                    {
                        newFormedTable[k, i] = Math.Abs(table[k, i] - colMins[i]);
                    }
                }
            }
            return newFormedTable;
        }
        public static string LaplaceDecision (decimal[,] table, bool NormalOrNot)
        {
            int alternative = 0;
            if (NormalOrNot)
            {
                decimal[] averages = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    averages[i] = row.Sum() / row.Length;
                }
                for (int i = 0; i < rows; i++)
                {
                    if (averages[i] == averages.Max())
                    {
                        alternative = i;
                    }
                }
            }
            else
            {
                decimal[] averages = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    averages[i] = row.Sum() / row.Length;
                }
                for (int i = 0; i < rows; i++)
                {
                    if (averages[i] == averages.Min())
                    {
                        alternative = i;
                    }
                }
            }
            return alternatives[alternative];
        }
       public static string HurwiczDecision( decimal[,] table,bool NormalOrNot,decimal optimism)
        {
            int alternative = 0;
            if (NormalOrNot)
            {
                decimal[] sums = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    sums[i] = row.Max() * optimism + row.Min() * (1m - optimism);
                }
                for (int i = 0; i < rows; i++)
                {
                    if (sums[i]==sums.Max())
                    {
                        alternative = i;
                    }
                }
            }
            else
            {
                decimal[] sums = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    sums[i] = row.Min() * optimism + row.Max() * (1m - optimism);
                }
                for (int i = 0; i < rows; i++)
                {
                    if (sums[i] == sums.Min())
                    {
                        alternative = i;
                    }
                }
            }
            return alternatives[alternative];
        }
        public static string TotalOptimisticDecision(decimal[,] table,bool NormalOrNot)
        {
            int alternative = 0;
            if (NormalOrNot)
            {
                decimal[] maximums = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    maximums[i] = row.Max();
                }
                for (int i = 0; i < rows; i++)
                {
                    if (maximums[i]==maximums.Max())
                    {
                        alternative = i;
                    }
                }
            }
            else
            {
                decimal[] minimums = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    minimums[i] = row.Min();
                }
                for (int i = 0; i < rows; i++)
                {
                    if (minimums[i] == minimums.Min())
                    {
                        alternative = i;
                    }
                }
            }
            return alternatives[alternative];
        }
       public static string WaldDecision (decimal[,] table,bool NormalOrNot)
        {
            int alternative = 0;
            if (NormalOrNot)
            {
                decimal[] minimums = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    minimums[i] = row.Min();
                }
                decimal maxMin = minimums.Max();
                for (int i = 0; i < rows; i++)
                {
                    if (maxMin == minimums[i])
                    {
                        alternative = i;
                    }
                }
            }
            else
            {
                decimal[] maximums = new decimal[rows];
                for (int i = 0; i < rows; i++)
                {
                    decimal[] row = new decimal[cols];
                    for (int k = 0; k < cols; k++)
                    {
                        row[k] = table[i, k];
                    }
                    maximums[i] = row.Max();
                }
                decimal minMax = maximums.Min();
                for (int i = 0; i < rows; i++)
                {
                    if (minMax == maximums[i])
                    {
                        alternative = i;
                    }
                }
            }
            return alternatives[alternative];
        }
       public static string UnderRisk (decimal [,] table,decimal [] probabilities, bool NormalOrNot)
        {
            decimal[] sums = new decimal[11];
            for (int i = 0; i < rows; i++)
            {
                for (int k = 0; k < cols; k++)
                {
                    sums[i] += probabilities[k] * table[i, k];
                }
            }
            int alternative = 0;
            if (NormalOrNot)
            {
                decimal maxSum = sums.Max();
                for (int i = 0; i < rows; i++)
                {
                    if (maxSum == sums[i])
                    {
                        alternative = i;
                        break;
                    }
                }
            }
            else
            {
                decimal minSum = sums.Min();
                for (int i = 0; i < rows; i++)
                {
                    if (minSum == sums[i])
                    {
                        alternative = i;
                        break;
                    }
                }
            }
            return alternatives[alternative];
        }
       public static string UnderCertainty (decimal[,] table, string columnNum, bool NormalOrNot)
        {
            int col = 0;
            for (int i = 0; i < columnNames.Length; i++)
            {
                if (columnNames[i]==columnNum)
                {
                    col = i;
                    break;
                }
            }
            decimal[] column = new decimal[rows];
            for (int i = 0; i < rows; i++)
            {
                column[i] = table[i,col];
            }
            if (NormalOrNot)
            {
                for (int i = 0; i < column.Length; i++)
                {
                    if (column[i] == column.Max())
                    {
                        col = i;
                        break;
                    }
                }
            }
            else
            {
                for (int i = 0; i < column.Length; i++)
                {
                    if (column[i] == column.Min())
                    {
                        col = i;
                        break;
                    }
                }
            }
            return alternatives[col];
        }
    }
}
