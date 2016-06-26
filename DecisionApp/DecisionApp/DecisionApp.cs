using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Windows.Forms;

namespace DecisionApp
{
    public partial class DecisionApp : Form
    {
        public DecisionApp()
        {
            InitializeComponent();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void ResultButton_Click(object sender, EventArgs e)
        {
            ResultFind.rows = int.Parse(comboBox1.SelectedItem.ToString());
            ResultFind.cols = int.Parse(comboBox2.SelectedItem.ToString());
            List<string> results = new List<string>();
                for (int i = 1; i <= ResultFind.cols; i++)
                {
                    dataGridView1.Rows[1].Cells[i].ValueType = typeof(decimal);
                }
                dataGridView1.Rows[1].Cells[1].ValueType=typeof(decimal);
                List<List<decimal>> filledTable = new List<List<decimal>>();
                ResultFind.probabilities = new decimal[ResultFind.cols];
                ResultFind.columnName = dataGridView1.Rows[1].Cells[ResultFind.cols + 2].Value.ToString();
                ResultFind.optimismCoef = decimal.Parse(dataGridView1.Rows[0].Cells[ResultFind.cols + 2].Value.ToString());
                for (int i = 1, k = 0; i < ResultFind.cols + 1; i++, k++)
                {
                    decimal probability = 1;
                    probability = decimal.Parse(dataGridView1.Rows[1].Cells[i].Value.ToString());
                    ResultFind.probabilities[k] = probability;
                }
                string type = (dataGridView1.Rows[2].Cells[ResultFind.cols + 2].Value.ToString());
                if (type == "Normal" || type == "normal")
                {
                    ResultFind.normal = true;
                }
                else if (type == "Inverted" || type == "inverted")
                {
                    ResultFind.normal = false;
                }

                for (int i = 2; i < ResultFind.rows + 2; i++)
                {
                    List<decimal> row = new List<decimal>(ResultFind.cols);
                    for (int k = 1; k < ResultFind.cols + 1; k++)
                    {
                        string input = dataGridView1.Rows[i].Cells[k].Value.ToString();
                        row.Add(decimal.Parse(input));
                    }
                    filledTable.Add(row);
                }

                decimal[,] table = new decimal[ResultFind.rows, ResultFind.cols];
                for (int i = 0; i < ResultFind.rows; i++)
                {
                    decimal[] row = filledTable[i].ToArray();
                    for (int k = 0; k < ResultFind.cols; k++)
                    {
                        table[i, k] = row[k];

                    }
                }
                for (int i = 2; i < ResultFind.rows+2; i++)
                {
                    ResultFind.alternatives.Add(dataGridView1.Rows[i].Cells[0].Value.ToString());
                }
                results.Add(ResultFind.UnderCertainty(table, ResultFind.columnName, ResultFind.normal));
                results.Add(ResultFind.UnderRisk(table, ResultFind.probabilities, ResultFind.normal));
                results.Add(ResultFind.WaldDecision(table, ResultFind.normal));
                results.Add(ResultFind.TotalOptimisticDecision(table, ResultFind.normal));
                results.Add(ResultFind.HurwiczDecision(table, ResultFind.normal, ResultFind.optimismCoef));
                results.Add(ResultFind.LaplaceDecision(table, ResultFind.normal));
                results.Add(ResultFind.WaldDecision(ResultFind.SavageDecision(table, ResultFind.normal), false));
            //
            if (yesOrNo.Items[0] == yesOrNo.SelectedItem)
            {


                ResultFind.CreatingTable(results, table);
            }

            //
            yesOrNo.Visible = false;
            wheretoWriteText.Visible = false;
            dataGridView1.Visible = false;
            ResultButton.Visible = false;
            ResultLabel.Visible = true;

            UnderCertaintyAnswer.Text = results[0];
            UnderRiskAnswer.Text = results[1];
            WaldDecisionAnswer.Text = results[2];
            TotalOptimisticDecisionAnswer.Text = results[3];
            HurwiczDecisionAnswer.Text = results[4];
            LaplaceDecisionAnswer.Text = results[5];
            SavageDecisionAnswer.Text = results[6];
            UnderCertaintyLabel.Visible = true;
            UnderCertaintyAnswer.Visible = true;
            UnderRiskLabel.Visible = true;
            UnderRiskAnswer.Visible = true;
            WaldDecisionLabel.Visible = true;
            WaldDecisionAnswer.Visible = true;
            TotalOptimisticDecisionLabel.Visible = true;
            TotalOptimisticDecisionAnswer.Visible = true;
            HurwiczDecisionLabel.Visible = true;
            HurwiczDecisionAnswer.Visible = true;
            LaplaceDecisionLabel.Visible = true;
            LaplaceDecisionAnswer.Visible = true;
            SavageDecisionLabel.Visible = true;
            SavageDecisionAnswer.Visible = true;
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            
        }

        private void TableCreate_Click(object sender, EventArgs e)
        {
            comboBox1.Visible = false;
            comboBox2.Visible = false;
            textBox1.Visible = false;
            textBox2.Visible = false;
            Title.Visible = false;
            TableCreate.Visible = false;
            ResultButton.Visible = true;
            yesOrNo.Visible = true;
            wheretoWriteText.Visible = true;
            int row = int.Parse(comboBox1.SelectedItem.ToString());
            int col = int.Parse(comboBox2.SelectedItem.ToString());
                dataGridView1.ColumnCount = col+3;
                dataGridView1.RowCount = row+2;
                dataGridView1.DefaultCellStyle.ForeColor = Color.Black;
            dataGridView1.Rows[1].Cells[1].ToolTipText = "Replace \"Probability\" with the probability coeficient of the State like\"0.3\"";
                for (int i = 1; i < col + 1; i++)
                {
                    dataGridView1.Rows[0].Cells[i].Value = "State " + (i);
                    dataGridView1.Rows[1].Cells[i].Value = "Probability";

                }
            dataGridView1.Rows[0].Cells[col + 2].ToolTipText = "Enter the Optimism coefficient";
                dataGridView1.Rows[0].Cells[col + 1].Value = "Optimism coefficient :";
            dataGridView1.Rows[1].Cells[col + 2].ToolTipText = "Enter the name of the State under certainty";
                dataGridView1.Rows[1].Cells[col + 1].Value = "State under certainty :";
            dataGridView1.Rows[2].Cells[col + 2].ToolTipText = "Enter the type of the matrix(Normal or Inverted)";
                dataGridView1.Rows[2].Cells[col + 1].Value = "Type of the matrix :";
            dataGridView1.Rows[2].Cells[1].ToolTipText = "Please fill in the table ";
                for (int i = 2; i < row + 2; i++)
                {
                    dataGridView1.Rows[i].Cells[0].Value = "Alternative " + (i - 1);
                }
            dataGridView1.Rows[2].Cells[0].ToolTipText = "You may change the name of the Alternatives as you like";
                dataGridView1.Visible = true;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            WelcomeLabel.Visible = false;
            ProceedButton.Visible = false;
            TableCreate.Visible = true;
            comboBox1.Visible = true;
            comboBox2.Visible = true;
            textBox1.Visible = true;
            textBox2.Visible = true;

        }

        private void Title_Click(object sender, EventArgs e)
        {

        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
