namespace guitar_tuner_final_build
{
    partial class Form1
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
            this.components = new System.ComponentModel.Container();
            this.cmbInput = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.cmbString = new System.Windows.Forms.ComboBox();
            this.btnStart = new System.Windows.Forms.Button();
            this.btnEnd = new System.Windows.Forms.Button();
            this.btnExit = new System.Windows.Forms.Button();
            this.btnRefresh = new System.Windows.Forms.Button();
            this.txtFreq = new System.Windows.Forms.TextBox();
            this.lblFreq = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.txtTarget = new System.Windows.Forms.TextBox();
            this.txtCenter = new System.Windows.Forms.TextBox();
            this.txtRight1 = new System.Windows.Forms.TextBox();
            this.txtRight2 = new System.Windows.Forms.TextBox();
            this.txtRight3 = new System.Windows.Forms.TextBox();
            this.txtLeft1 = new System.Windows.Forms.TextBox();
            this.txtLeft2 = new System.Windows.Forms.TextBox();
            this.txtLeft3 = new System.Windows.Forms.TextBox();
            this.timer1 = new System.Windows.Forms.Timer(this.components);
            this.label4 = new System.Windows.Forms.Label();
            this.cmbAlgorithm = new System.Windows.Forms.ComboBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txtNotePlayed = new System.Windows.Forms.TextBox();
            this.btnHelp = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // cmbInput
            // 
            this.cmbInput.FormattingEnabled = true;
            this.cmbInput.Location = new System.Drawing.Point(190, 92);
            this.cmbInput.Name = "cmbInput";
            this.cmbInput.Size = new System.Drawing.Size(241, 21);
            this.cmbInput.TabIndex = 0;
            this.cmbInput.SelectedIndexChanged += new System.EventHandler(this.cmbInput_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label1.Location = new System.Drawing.Point(98, 92);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(86, 17);
            this.label1.TabIndex = 1;
            this.label1.Text = "Input Device";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F);
            this.label2.Location = new System.Drawing.Point(110, 167);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(43, 20);
            this.label2.TabIndex = 2;
            this.label2.Text = "Note";
            // 
            // cmbString
            // 
            this.cmbString.FormattingEnabled = true;
            this.cmbString.Location = new System.Drawing.Point(167, 166);
            this.cmbString.Name = "cmbString";
            this.cmbString.Size = new System.Drawing.Size(54, 21);
            this.cmbString.TabIndex = 3;
            this.cmbString.Visible = false;
            this.cmbString.SelectedIndexChanged += new System.EventHandler(this.cmbString_SelectedIndexChanged);
            // 
            // btnStart
            // 
            this.btnStart.Location = new System.Drawing.Point(227, 164);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(69, 23);
            this.btnStart.TabIndex = 4;
            this.btnStart.Text = "Start";
            this.btnStart.UseVisualStyleBackColor = true;
            this.btnStart.Visible = false;
            this.btnStart.Click += new System.EventHandler(this.btnStart_Click);
            // 
            // btnEnd
            // 
            this.btnEnd.Location = new System.Drawing.Point(302, 164);
            this.btnEnd.Name = "btnEnd";
            this.btnEnd.Size = new System.Drawing.Size(66, 23);
            this.btnEnd.TabIndex = 5;
            this.btnEnd.Text = "End";
            this.btnEnd.UseVisualStyleBackColor = true;
            this.btnEnd.Visible = false;
            this.btnEnd.Click += new System.EventHandler(this.btnEnd_Click);
            // 
            // btnExit
            // 
            this.btnExit.Location = new System.Drawing.Point(364, 361);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(97, 38);
            this.btnExit.TabIndex = 6;
            this.btnExit.Text = "Exit";
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // btnRefresh
            // 
            this.btnRefresh.Location = new System.Drawing.Point(23, 89);
            this.btnRefresh.Name = "btnRefresh";
            this.btnRefresh.Size = new System.Drawing.Size(69, 23);
            this.btnRefresh.TabIndex = 7;
            this.btnRefresh.Text = "Refresh";
            this.btnRefresh.UseVisualStyleBackColor = true;
            this.btnRefresh.Click += new System.EventHandler(this.btnRefresh_Click);
            // 
            // txtFreq
            // 
            this.txtFreq.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.txtFreq.Location = new System.Drawing.Point(287, 303);
            this.txtFreq.Name = "txtFreq";
            this.txtFreq.ReadOnly = true;
            this.txtFreq.Size = new System.Drawing.Size(76, 32);
            this.txtFreq.TabIndex = 8;
            // 
            // lblFreq
            // 
            this.lblFreq.AutoSize = true;
            this.lblFreq.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F);
            this.lblFreq.Location = new System.Drawing.Point(369, 311);
            this.lblFreq.Name = "lblFreq";
            this.lblFreq.Size = new System.Drawing.Size(84, 20);
            this.lblFreq.TabIndex = 9;
            this.lblFreq.Text = "Frequency";
            this.lblFreq.Click += new System.EventHandler(this.lblFreq_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F);
            this.label3.Location = new System.Drawing.Point(50, 310);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(134, 20);
            this.label3.TabIndex = 12;
            this.label3.Text = "Target Frequency";
            // 
            // txtTarget
            // 
            this.txtTarget.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.txtTarget.Location = new System.Drawing.Point(190, 303);
            this.txtTarget.Name = "txtTarget";
            this.txtTarget.ReadOnly = true;
            this.txtTarget.Size = new System.Drawing.Size(76, 32);
            this.txtTarget.TabIndex = 13;
            // 
            // txtCenter
            // 
            this.txtCenter.BackColor = System.Drawing.SystemColors.Desktop;
            this.txtCenter.Location = new System.Drawing.Point(241, 233);
            this.txtCenter.Name = "txtCenter";
            this.txtCenter.ReadOnly = true;
            this.txtCenter.Size = new System.Drawing.Size(39, 20);
            this.txtCenter.TabIndex = 14;
            // 
            // txtRight1
            // 
            this.txtRight1.BackColor = System.Drawing.SystemColors.Desktop;
            this.txtRight1.Location = new System.Drawing.Point(286, 233);
            this.txtRight1.Name = "txtRight1";
            this.txtRight1.ReadOnly = true;
            this.txtRight1.Size = new System.Drawing.Size(39, 20);
            this.txtRight1.TabIndex = 15;
            // 
            // txtRight2
            // 
            this.txtRight2.BackColor = System.Drawing.SystemColors.Desktop;
            this.txtRight2.Location = new System.Drawing.Point(331, 233);
            this.txtRight2.Name = "txtRight2";
            this.txtRight2.ReadOnly = true;
            this.txtRight2.Size = new System.Drawing.Size(39, 20);
            this.txtRight2.TabIndex = 16;
            // 
            // txtRight3
            // 
            this.txtRight3.BackColor = System.Drawing.SystemColors.Desktop;
            this.txtRight3.Location = new System.Drawing.Point(376, 233);
            this.txtRight3.Name = "txtRight3";
            this.txtRight3.ReadOnly = true;
            this.txtRight3.Size = new System.Drawing.Size(39, 20);
            this.txtRight3.TabIndex = 17;
            // 
            // txtLeft1
            // 
            this.txtLeft1.BackColor = System.Drawing.SystemColors.Desktop;
            this.txtLeft1.Location = new System.Drawing.Point(196, 233);
            this.txtLeft1.Name = "txtLeft1";
            this.txtLeft1.ReadOnly = true;
            this.txtLeft1.Size = new System.Drawing.Size(39, 20);
            this.txtLeft1.TabIndex = 18;
            // 
            // txtLeft2
            // 
            this.txtLeft2.BackColor = System.Drawing.SystemColors.Desktop;
            this.txtLeft2.Location = new System.Drawing.Point(151, 233);
            this.txtLeft2.Name = "txtLeft2";
            this.txtLeft2.ReadOnly = true;
            this.txtLeft2.Size = new System.Drawing.Size(39, 20);
            this.txtLeft2.TabIndex = 19;
            // 
            // txtLeft3
            // 
            this.txtLeft3.BackColor = System.Drawing.SystemColors.Desktop;
            this.txtLeft3.Location = new System.Drawing.Point(106, 233);
            this.txtLeft3.Name = "txtLeft3";
            this.txtLeft3.ReadOnly = true;
            this.txtLeft3.Size = new System.Drawing.Size(39, 20);
            this.txtLeft3.TabIndex = 20;
            // 
            // timer1
            // 
            this.timer1.Enabled = true;
            this.timer1.Interval = 10;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 10F);
            this.label4.Location = new System.Drawing.Point(20, 125);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(149, 17);
            this.label4.TabIndex = 21;
            this.label4.Text = "Processing Technique";
            // 
            // cmbAlgorithm
            // 
            this.cmbAlgorithm.FormattingEnabled = true;
            this.cmbAlgorithm.Location = new System.Drawing.Point(190, 124);
            this.cmbAlgorithm.Name = "cmbAlgorithm";
            this.cmbAlgorithm.Size = new System.Drawing.Size(241, 21);
            this.cmbAlgorithm.TabIndex = 22;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F);
            this.label5.Location = new System.Drawing.Point(142, 361);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(94, 20);
            this.label5.TabIndex = 23;
            this.label5.Text = "Note Played";
            // 
            // txtNotePlayed
            // 
            this.txtNotePlayed.Font = new System.Drawing.Font("Microsoft Sans Serif", 16F);
            this.txtNotePlayed.Location = new System.Drawing.Point(242, 354);
            this.txtNotePlayed.Name = "txtNotePlayed";
            this.txtNotePlayed.ReadOnly = true;
            this.txtNotePlayed.Size = new System.Drawing.Size(55, 32);
            this.txtNotePlayed.TabIndex = 24;
            // 
            // btnHelp
            // 
            this.btnHelp.Location = new System.Drawing.Point(196, 12);
            this.btnHelp.Name = "btnHelp";
            this.btnHelp.Size = new System.Drawing.Size(110, 42);
            this.btnHelp.TabIndex = 25;
            this.btnHelp.Text = "How to Use";
            this.btnHelp.UseVisualStyleBackColor = true;
            this.btnHelp.Click += new System.EventHandler(this.btnHelp_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(473, 411);
            this.Controls.Add(this.btnHelp);
            this.Controls.Add(this.txtNotePlayed);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.cmbAlgorithm);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txtLeft3);
            this.Controls.Add(this.txtLeft2);
            this.Controls.Add(this.txtLeft1);
            this.Controls.Add(this.txtRight3);
            this.Controls.Add(this.txtRight2);
            this.Controls.Add(this.txtRight1);
            this.Controls.Add(this.txtCenter);
            this.Controls.Add(this.txtTarget);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.lblFreq);
            this.Controls.Add(this.txtFreq);
            this.Controls.Add(this.btnRefresh);
            this.Controls.Add(this.btnExit);
            this.Controls.Add(this.btnEnd);
            this.Controls.Add(this.btnStart);
            this.Controls.Add(this.cmbString);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.cmbInput);
            this.Name = "Form1";
            this.Text = "Rob Tuner";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox cmbInput;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox cmbString;
        private System.Windows.Forms.Button btnStart;
        private System.Windows.Forms.Button btnEnd;
        private System.Windows.Forms.Button btnExit;
        private System.Windows.Forms.Button btnRefresh;
        private System.Windows.Forms.TextBox txtFreq;
        private System.Windows.Forms.Label lblFreq;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txtTarget;
        private System.Windows.Forms.TextBox txtCenter;
        private System.Windows.Forms.TextBox txtRight1;
        private System.Windows.Forms.TextBox txtRight2;
        private System.Windows.Forms.TextBox txtRight3;
        private System.Windows.Forms.TextBox txtLeft1;
        private System.Windows.Forms.TextBox txtLeft2;
        private System.Windows.Forms.TextBox txtLeft3;
        private System.Windows.Forms.Timer timer1;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.ComboBox cmbAlgorithm;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txtNotePlayed;
        private System.Windows.Forms.Button btnHelp;
    }
}

