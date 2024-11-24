package Timer;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;

public class ClockTimer implements ActionListener 
{
    int seconds = 0;
    int screenHeight = 350;
    int notetimerheight = 230;
    ArrayList<JLabel> removelabel = new ArrayList<>();

    JFrame frame;
    Timer swingTimer; 
    JLabel timer, notelabel;
    JButton start, stop, reset, note, fasttimer,normal;
    Font timerFont = new Font("Times New Roman", Font.BOLD, 50);
    Font buttonFont = new Font("Arial", Font.PLAIN, 16);
    Font font = new Font("Arial", Font.BOLD, 24);

    public ClockTimer()
    {
        frame = new JFrame("Clock");
        frame.setSize(500, screenHeight);
        frame.getContentPane().setBackground(Color.darkGray);

        timer = new JLabel("00 : 00");
        timer.setBounds(40, 95, 150, 60);
        timer.setFont(timerFont);
        timer.setForeground(Color.WHITE);

        notelabel = new JLabel("Note:");
        notelabel.setBounds(40, 210, 150, 60);
        notelabel.setFont(font);
        notelabel.setForeground(Color.WHITE);

        start = new JButton("Start");
        start.setBounds(250, 50, 90, 40);
        start.setFont(buttonFont);
        start.addActionListener(this);

        stop = new JButton("Stop");
        stop.setBounds(350, 50, 90, 40);
        stop.setFont(buttonFont);
        stop.addActionListener(this);
        
        reset = new JButton("Reset");
        reset.setBounds(250, 100, 90, 40);
        reset.setFont(buttonFont);
        reset.addActionListener(this);

        note = new JButton("Note");
        note.setBounds(350, 100, 90, 40);
        note.setFont(buttonFont);
        note.addActionListener(this);

        normal = new JButton("Normal");
        normal.setBounds(250, 150, 90, 40);
        normal.setFont(buttonFont);
        normal.addActionListener(this);

        fasttimer = new JButton("2x");
        fasttimer.setBounds(350, 150, 90, 40);
        fasttimer.setFont(buttonFont);
        fasttimer.addActionListener(this);

        frame.add(timer);
        frame.add(start);
        frame.add(reset);
        frame.add(note);
        frame.add(notelabel);
        frame.add(stop);
        frame.add(fasttimer);
        frame.add(normal);

        frame.setLayout(null);
        frame.setVisible(true);

        swingTimer = new Timer(1000, new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e)
            {
                seconds++;
                updateTimerLabel();
            }
        });
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == start) {
            swingTimer.start();
        }
        
        else if (e.getSource() == stop)
        {
            swingTimer.stop();
        }

        else if (e.getSource() == reset) 
        {
            swingTimer.setDelay(1000);
            swingTimer.stop();
            seconds = 0;
            screenHeight = 350;
            notetimerheight = 230;
            
            frame.setSize(500, screenHeight);
            resetlabel();
            updateTimerLabel();
        }

        else if(e.getSource() == note)
        {
            note();
        }

        else if(e.getSource() == fasttimer)
        {
            swingTimer.setDelay(200);
        }

        else if(e.getSource() == normal)
        {
            swingTimer.setDelay(1000);
        }
    }

    public void updateTimerLabel()
    {
        int mins = seconds / 60;
        int secs = seconds % 60;
        timer.setText(String.format("%02d : %02d", mins, secs));
    }

    public void note()
    {
        notetimerheight += 30;
        screenHeight += 30;
        
        JLabel notetimer = new JLabel("");
        notetimer.setBounds(40, notetimerheight, 150, 60);
        notetimer.setForeground(Color.WHITE);
        notetimer.setFont(buttonFont);
        removelabel.add(notetimer);

        frame.add(notetimer);
        frame.setSize(500, screenHeight);
        
        String labelfromtimer = timer.getText();
        notetimer.setText(String.format("- " + labelfromtimer));
    }

    public void resetlabel()
    {
        for (JLabel label : new ArrayList<>(removelabel)) 
        {
            frame.remove(label);
        }
        removelabel.clear();
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args)
    {
        new ClockTimer();
    }
}