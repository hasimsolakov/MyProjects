namespace DecisionApp
{
    partial class DecisionApp
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DecisionApp));
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.Title = new System.Windows.Forms.Label();
            this.ResultButton = new System.Windows.Forms.Button();
            this.comboBox2 = new System.Windows.Forms.ComboBox();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.TableCreate = new System.Windows.Forms.Button();
            this.ProceedButton = new System.Windows.Forms.Button();
            this.WelcomeLabel = new System.Windows.Forms.Label();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.ResultLabel = new System.Windows.Forms.Label();
            this.UnderCertaintyLabel = new System.Windows.Forms.Label();
            this.UnderRiskLabel = new System.Windows.Forms.Label();
            this.WaldDecisionLabel = new System.Windows.Forms.Label();
            this.TotalOptimisticDecisionLabel = new System.Windows.Forms.Label();
            this.HurwiczDecisionLabel = new System.Windows.Forms.Label();
            this.LaplaceDecisionLabel = new System.Windows.Forms.Label();
            this.SavageDecisionLabel = new System.Windows.Forms.Label();
            this.UnderCertaintyAnswer = new System.Windows.Forms.TextBox();
            this.UnderRiskAnswer = new System.Windows.Forms.TextBox();
            this.WaldDecisionAnswer = new System.Windows.Forms.TextBox();
            this.TotalOptimisticDecisionAnswer = new System.Windows.Forms.TextBox();
            this.HurwiczDecisionAnswer = new System.Windows.Forms.TextBox();
            this.LaplaceDecisionAnswer = new System.Windows.Forms.TextBox();
            this.SavageDecisionAnswer = new System.Windows.Forms.TextBox();
            this.wheretoWriteText = new System.Windows.Forms.TextBox();
            this.yesOrNo = new System.Windows.Forms.ComboBox();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Items.AddRange(new object[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11 "});
            this.comboBox1.Location = new System.Drawing.Point(91, 78);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(170, 21);
            this.comboBox1.TabIndex = 0;
            this.comboBox1.Visible = false;
            this.comboBox1.SelectedIndexChanged += new System.EventHandler(this.comboBox1_SelectedIndexChanged);
            // 
            // Title
            // 
            this.Title.AutoSize = true;
            this.Title.BackColor = System.Drawing.Color.Transparent;
            this.Title.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.Title.Location = new System.Drawing.Point(175, 116);
            this.Title.Name = "Title";
            this.Title.Size = new System.Drawing.Size(465, 37);
            this.Title.TabIndex = 1;
            this.Title.Text = "The Decision Matrix Application";
            this.Title.Click += new System.EventHandler(this.Title_Click);
            // 
            // ResultButton
            // 
            this.ResultButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.ResultButton.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.ResultButton.Location = new System.Drawing.Point(649, 407);
            this.ResultButton.Name = "ResultButton";
            this.ResultButton.Size = new System.Drawing.Size(123, 32);
            this.ResultButton.TabIndex = 2;
            this.ResultButton.Text = "Get Result";
            this.ResultButton.UseVisualStyleBackColor = true;
            this.ResultButton.Visible = false;
            this.ResultButton.Click += new System.EventHandler(this.ResultButton_Click);
            // 
            // comboBox2
            // 
            this.comboBox2.FormattingEnabled = true;
            this.comboBox2.Items.AddRange(new object[] {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"});
            this.comboBox2.Location = new System.Drawing.Point(552, 78);
            this.comboBox2.Name = "comboBox2";
            this.comboBox2.Size = new System.Drawing.Size(170, 21);
            this.comboBox2.TabIndex = 3;
            this.comboBox2.Visible = false;
            // 
            // dataGridView1
            // 
            this.dataGridView1.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCellsExceptHeader;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.ColumnHeadersVisible = false;
            this.dataGridView1.Location = new System.Drawing.Point(0, -1);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.Size = new System.Drawing.Size(797, 402);
            this.dataGridView1.TabIndex = 4;
            this.dataGridView1.Visible = false;
            this.dataGridView1.CellContentClick += new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellContentClick);
            // 
            // TableCreate
            // 
            this.TableCreate.BackColor = System.Drawing.SystemColors.ButtonShadow;
            this.TableCreate.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.TableCreate.ForeColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.TableCreate.Location = new System.Drawing.Point(649, 407);
            this.TableCreate.Name = "TableCreate";
            this.TableCreate.Size = new System.Drawing.Size(123, 32);
            this.TableCreate.TabIndex = 5;
            this.TableCreate.Text = "CreateTable";
            this.TableCreate.UseVisualStyleBackColor = false;
            this.TableCreate.Visible = false;
            this.TableCreate.Click += new System.EventHandler(this.TableCreate_Click);
            // 
            // ProceedButton
            // 
            this.ProceedButton.AutoSize = true;
            this.ProceedButton.BackColor = System.Drawing.SystemColors.ActiveCaptionText;
            this.ProceedButton.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.ProceedButton.Location = new System.Drawing.Point(332, 381);
            this.ProceedButton.Name = "ProceedButton";
            this.ProceedButton.Size = new System.Drawing.Size(78, 30);
            this.ProceedButton.TabIndex = 7;
            this.ProceedButton.Text = "Proceed";
            this.ProceedButton.UseVisualStyleBackColor = false;
            this.ProceedButton.Click += new System.EventHandler(this.button1_Click);
            // 
            // WelcomeLabel
            // 
            this.WelcomeLabel.AutoSize = true;
            this.WelcomeLabel.BackColor = System.Drawing.Color.Transparent;
            this.WelcomeLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.WelcomeLabel.Location = new System.Drawing.Point(315, 63);
            this.WelcomeLabel.Name = "WelcomeLabel";
            this.WelcomeLabel.Size = new System.Drawing.Size(186, 37);
            this.WelcomeLabel.TabIndex = 9;
            this.WelcomeLabel.Text = "Welcome to";
            // 
            // textBox1
            // 
            this.textBox1.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.textBox1.Location = new System.Drawing.Point(12, 46);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(286, 26);
            this.textBox1.TabIndex = 10;
            this.textBox1.Text = "Choose the number of the \r\nAlternatives";
            this.textBox1.Visible = false;
            this.textBox1.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // textBox2
            // 
            this.textBox2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.textBox2.Location = new System.Drawing.Point(522, 46);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(250, 26);
            this.textBox2.TabIndex = 11;
            this.textBox2.Text = "Choose the number of the States";
            this.textBox2.Visible = false;
            // 
            // ResultLabel
            // 
            this.ResultLabel.AutoSize = true;
            this.ResultLabel.BackColor = System.Drawing.Color.Transparent;
            this.ResultLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.ResultLabel.Location = new System.Drawing.Point(346, 26);
            this.ResultLabel.Name = "ResultLabel";
            this.ResultLabel.Size = new System.Drawing.Size(122, 37);
            this.ResultLabel.TabIndex = 13;
            this.ResultLabel.Text = "Results";
            this.ResultLabel.Visible = false;
            // 
            // UnderCertaintyLabel
            // 
            this.UnderCertaintyLabel.AutoSize = true;
            this.UnderCertaintyLabel.BackColor = System.Drawing.Color.Transparent;
            this.UnderCertaintyLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.UnderCertaintyLabel.Location = new System.Drawing.Point(141, 85);
            this.UnderCertaintyLabel.Name = "UnderCertaintyLabel";
            this.UnderCertaintyLabel.Size = new System.Drawing.Size(205, 31);
            this.UnderCertaintyLabel.TabIndex = 14;
            this.UnderCertaintyLabel.Text = "Under Certainty";
            this.UnderCertaintyLabel.Visible = false;
            // 
            // UnderRiskLabel
            // 
            this.UnderRiskLabel.AutoSize = true;
            this.UnderRiskLabel.BackColor = System.Drawing.Color.Transparent;
            this.UnderRiskLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.UnderRiskLabel.Location = new System.Drawing.Point(141, 133);
            this.UnderRiskLabel.Name = "UnderRiskLabel";
            this.UnderRiskLabel.Size = new System.Drawing.Size(149, 31);
            this.UnderRiskLabel.TabIndex = 15;
            this.UnderRiskLabel.Text = "Under Risk";
            this.UnderRiskLabel.Visible = false;
            // 
            // WaldDecisionLabel
            // 
            this.WaldDecisionLabel.AutoSize = true;
            this.WaldDecisionLabel.BackColor = System.Drawing.Color.Transparent;
            this.WaldDecisionLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.WaldDecisionLabel.Location = new System.Drawing.Point(141, 173);
            this.WaldDecisionLabel.Name = "WaldDecisionLabel";
            this.WaldDecisionLabel.Size = new System.Drawing.Size(187, 31);
            this.WaldDecisionLabel.TabIndex = 16;
            this.WaldDecisionLabel.Text = "Wald Decision";
            this.WaldDecisionLabel.Visible = false;
            // 
            // TotalOptimisticDecisionLabel
            // 
            this.TotalOptimisticDecisionLabel.AutoSize = true;
            this.TotalOptimisticDecisionLabel.BackColor = System.Drawing.Color.Transparent;
            this.TotalOptimisticDecisionLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.TotalOptimisticDecisionLabel.Location = new System.Drawing.Point(141, 216);
            this.TotalOptimisticDecisionLabel.Name = "TotalOptimisticDecisionLabel";
            this.TotalOptimisticDecisionLabel.Size = new System.Drawing.Size(314, 31);
            this.TotalOptimisticDecisionLabel.TabIndex = 17;
            this.TotalOptimisticDecisionLabel.Text = "Total Optimistic Decision";
            this.TotalOptimisticDecisionLabel.Visible = false;
            // 
            // HurwiczDecisionLabel
            // 
            this.HurwiczDecisionLabel.AutoSize = true;
            this.HurwiczDecisionLabel.BackColor = System.Drawing.Color.Transparent;
            this.HurwiczDecisionLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.HurwiczDecisionLabel.Location = new System.Drawing.Point(141, 260);
            this.HurwiczDecisionLabel.Name = "HurwiczDecisionLabel";
            this.HurwiczDecisionLabel.Size = new System.Drawing.Size(224, 31);
            this.HurwiczDecisionLabel.TabIndex = 18;
            this.HurwiczDecisionLabel.Text = "Hurwicz Decision";
            this.HurwiczDecisionLabel.Visible = false;
            // 
            // LaplaceDecisionLabel
            // 
            this.LaplaceDecisionLabel.AutoSize = true;
            this.LaplaceDecisionLabel.BackColor = System.Drawing.Color.Transparent;
            this.LaplaceDecisionLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.LaplaceDecisionLabel.Location = new System.Drawing.Point(141, 305);
            this.LaplaceDecisionLabel.Name = "LaplaceDecisionLabel";
            this.LaplaceDecisionLabel.Size = new System.Drawing.Size(221, 31);
            this.LaplaceDecisionLabel.TabIndex = 19;
            this.LaplaceDecisionLabel.Text = "Laplace Decision";
            this.LaplaceDecisionLabel.Visible = false;
            // 
            // SavageDecisionLabel
            // 
            this.SavageDecisionLabel.AutoSize = true;
            this.SavageDecisionLabel.BackColor = System.Drawing.Color.Transparent;
            this.SavageDecisionLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.SavageDecisionLabel.Location = new System.Drawing.Point(141, 356);
            this.SavageDecisionLabel.Name = "SavageDecisionLabel";
            this.SavageDecisionLabel.Size = new System.Drawing.Size(218, 31);
            this.SavageDecisionLabel.TabIndex = 20;
            this.SavageDecisionLabel.Text = "Savage Decision";
            this.SavageDecisionLabel.Visible = false;
            // 
            // UnderCertaintyAnswer
            // 
            this.UnderCertaintyAnswer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.UnderCertaintyAnswer.Location = new System.Drawing.Point(484, 85);
            this.UnderCertaintyAnswer.Name = "UnderCertaintyAnswer";
            this.UnderCertaintyAnswer.Size = new System.Drawing.Size(210, 38);
            this.UnderCertaintyAnswer.TabIndex = 21;
            this.UnderCertaintyAnswer.Visible = false;
            // 
            // UnderRiskAnswer
            // 
            this.UnderRiskAnswer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.UnderRiskAnswer.Location = new System.Drawing.Point(484, 126);
            this.UnderRiskAnswer.Name = "UnderRiskAnswer";
            this.UnderRiskAnswer.Size = new System.Drawing.Size(210, 38);
            this.UnderRiskAnswer.TabIndex = 22;
            this.UnderRiskAnswer.Visible = false;
            // 
            // WaldDecisionAnswer
            // 
            this.WaldDecisionAnswer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.WaldDecisionAnswer.Location = new System.Drawing.Point(484, 168);
            this.WaldDecisionAnswer.Name = "WaldDecisionAnswer";
            this.WaldDecisionAnswer.Size = new System.Drawing.Size(210, 38);
            this.WaldDecisionAnswer.TabIndex = 23;
            this.WaldDecisionAnswer.Visible = false;
            // 
            // TotalOptimisticDecisionAnswer
            // 
            this.TotalOptimisticDecisionAnswer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.TotalOptimisticDecisionAnswer.Location = new System.Drawing.Point(484, 212);
            this.TotalOptimisticDecisionAnswer.Name = "TotalOptimisticDecisionAnswer";
            this.TotalOptimisticDecisionAnswer.Size = new System.Drawing.Size(210, 38);
            this.TotalOptimisticDecisionAnswer.TabIndex = 24;
            this.TotalOptimisticDecisionAnswer.Visible = false;
            // 
            // HurwiczDecisionAnswer
            // 
            this.HurwiczDecisionAnswer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.HurwiczDecisionAnswer.Location = new System.Drawing.Point(484, 257);
            this.HurwiczDecisionAnswer.Name = "HurwiczDecisionAnswer";
            this.HurwiczDecisionAnswer.Size = new System.Drawing.Size(210, 38);
            this.HurwiczDecisionAnswer.TabIndex = 25;
            this.HurwiczDecisionAnswer.Visible = false;
            // 
            // LaplaceDecisionAnswer
            // 
            this.LaplaceDecisionAnswer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.LaplaceDecisionAnswer.Location = new System.Drawing.Point(484, 302);
            this.LaplaceDecisionAnswer.Name = "LaplaceDecisionAnswer";
            this.LaplaceDecisionAnswer.Size = new System.Drawing.Size(210, 38);
            this.LaplaceDecisionAnswer.TabIndex = 26;
            this.LaplaceDecisionAnswer.Visible = false;
            // 
            // SavageDecisionAnswer
            // 
            this.SavageDecisionAnswer.Font = new System.Drawing.Font("Microsoft Sans Serif", 20F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.SavageDecisionAnswer.Location = new System.Drawing.Point(484, 349);
            this.SavageDecisionAnswer.Name = "SavageDecisionAnswer";
            this.SavageDecisionAnswer.Size = new System.Drawing.Size(210, 38);
            this.SavageDecisionAnswer.TabIndex = 27;
            this.SavageDecisionAnswer.Visible = false;
            // 
            // wheretoWriteText
            // 
            this.wheretoWriteText.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.wheretoWriteText.Location = new System.Drawing.Point(36, 393);
            this.wheretoWriteText.Name = "wheretoWriteText";
            this.wheretoWriteText.Size = new System.Drawing.Size(565, 29);
            this.wheretoWriteText.TabIndex = 28;
            this.wheretoWriteText.Text = "Would you like to save the table and the answers in Excel Table?";
            this.wheretoWriteText.Visible = false;
            // 
            // yesOrNo
            // 
            this.yesOrNo.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.yesOrNo.FormattingEnabled = true;
            this.yesOrNo.Items.AddRange(new object[] {
            "Yes",
            "No"});
            this.yesOrNo.Location = new System.Drawing.Point(480, 428);
            this.yesOrNo.Name = "yesOrNo";
            this.yesOrNo.Size = new System.Drawing.Size(121, 32);
            this.yesOrNo.TabIndex = 29;
            this.yesOrNo.Visible = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(796, 461);
            this.Controls.Add(this.yesOrNo);
            this.Controls.Add(this.wheretoWriteText);
            this.Controls.Add(this.SavageDecisionAnswer);
            this.Controls.Add(this.LaplaceDecisionAnswer);
            this.Controls.Add(this.HurwiczDecisionAnswer);
            this.Controls.Add(this.TotalOptimisticDecisionAnswer);
            this.Controls.Add(this.WaldDecisionAnswer);
            this.Controls.Add(this.UnderRiskAnswer);
            this.Controls.Add(this.UnderCertaintyAnswer);
            this.Controls.Add(this.SavageDecisionLabel);
            this.Controls.Add(this.LaplaceDecisionLabel);
            this.Controls.Add(this.HurwiczDecisionLabel);
            this.Controls.Add(this.TotalOptimisticDecisionLabel);
            this.Controls.Add(this.WaldDecisionLabel);
            this.Controls.Add(this.UnderRiskLabel);
            this.Controls.Add(this.UnderCertaintyLabel);
            this.Controls.Add(this.ResultLabel);
            this.Controls.Add(this.textBox2);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.WelcomeLabel);
            this.Controls.Add(this.ProceedButton);
            this.Controls.Add(this.TableCreate);
            this.Controls.Add(this.comboBox2);
            this.Controls.Add(this.ResultButton);
            this.Controls.Add(this.Title);
            this.Controls.Add(this.comboBox1);
            this.Controls.Add(this.dataGridView1);
            this.ForeColor = System.Drawing.SystemColors.ControlLight;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Form1";
            this.Text = "DecisionApp";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox comboBox1;
        private System.Windows.Forms.Label Title;
        private System.Windows.Forms.Button ResultButton;
        private System.Windows.Forms.ComboBox comboBox2;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button TableCreate;
        private System.Windows.Forms.Button ProceedButton;
        private System.Windows.Forms.Label WelcomeLabel;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.Label ResultLabel;
        private System.Windows.Forms.Label UnderCertaintyLabel;
        private System.Windows.Forms.Label UnderRiskLabel;
        private System.Windows.Forms.Label WaldDecisionLabel;
        private System.Windows.Forms.Label TotalOptimisticDecisionLabel;
        private System.Windows.Forms.Label HurwiczDecisionLabel;
        private System.Windows.Forms.Label LaplaceDecisionLabel;
        private System.Windows.Forms.Label SavageDecisionLabel;
        private System.Windows.Forms.TextBox UnderCertaintyAnswer;
        private System.Windows.Forms.TextBox UnderRiskAnswer;
        private System.Windows.Forms.TextBox WaldDecisionAnswer;
        private System.Windows.Forms.TextBox TotalOptimisticDecisionAnswer;
        private System.Windows.Forms.TextBox HurwiczDecisionAnswer;
        private System.Windows.Forms.TextBox LaplaceDecisionAnswer;
        private System.Windows.Forms.TextBox SavageDecisionAnswer;
        private System.Windows.Forms.TextBox wheretoWriteText;
        private System.Windows.Forms.ComboBox yesOrNo;
    }
}

