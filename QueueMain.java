import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import mycollections.EmptyQueueException;
import mycollections.NullObjectException;
import mycollections.Queue;

/**
 * The QueueMain class is for execution and testing of Queue class, allowing for the input queue elements via
 * the command line and thru a GUI (comma or space separated values).
 * @author Byung Seon Kim
 *
 */
public class QueueMain {

	/* CONSTRUCTOR	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/**
	 * Default constructor of QueueMain: if there are some parameters, insert parameters to queue
	 * @param args parameters to input queue
	 */
	public QueueMain( String[] args ) {
		queue = new Queue<>(); // create a Queue object
		for ( String arg : args ) {
			try {
				queue.enqueue( arg );
			}  catch ( NullObjectException e ) {
				System.out.println( e.getMessage() );
			}
		}
	}

	/* ACCESSORS	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/* MODIFIERS	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/* NORMAL BEHAVIOR	------------------------------------------------------------ the skeleton from Reginald Dyer */
	/* HELPER METHODS	------------------------------------------------------------ the skeleton from Reginald Dyer */

	/** create all GUI elements */
	private void initGUI() {

		JFrame jFrame = new JFrame("Test GUI for Queue (Lab 4)");
		GridBagLayout root = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		// compose JLabel to show what the queue has
		JPanel queuePanel = createQueueView();
		// compose JPanel to input string to enqueue
		JPanel inputPanel = createInput();
		// compose JPanel for button collection
		JPanel buttonPanel = createButtons();
		// compose JPanel for show progressing Enqueue test
		JPanel enProgPanel = createEnqueueProgress();
		// compose JPanel for show progressing Dequeue test
		JPanel deProgPanel = createDequeueProgress();

		jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jFrame.getContentPane().setLayout( root );

		c.gridx = 0;
		c.gridy = 0;
		// This field is used when the component's display area is larger than the component's requested size.
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 100;      //make this component tall
		c.ipadx = 250;
		//queuePanel.setMinimumSize(new Dimension(500, 300));
		jFrame.getContentPane().add( queuePanel, c );

		c.gridx = 0;
		c.gridy = 1;
		c.ipady = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		jFrame.getContentPane().add( inputPanel, c );

		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		jFrame.getContentPane().add( buttonPanel, c );

		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		jFrame.getContentPane().add( enProgPanel, c );
		
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		jFrame.getContentPane().add( deProgPanel, c );
		
		jFrame.pack(); // update JFrame size to fit content size

		// Set JFrame to the middle of the screen
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();      
		int x=(int)((dimension.getWidth() - jFrame.getWidth())/2);
		int y=(int)((dimension.getHeight() - jFrame.getHeight())/2);
		jFrame.setLocation(x, y); 

		jFrame.setVisible(true);
	}

	/** 
	 * Compose JLabel to show what the queue has
	 * @return JPanel to show what the queue has
	 */
	private JPanel createQueueView() {
		JPanel panel = new JPanel(new BorderLayout()); // create base panel object
		// panel.setBounds(0, 0, 500, 300); // Moves and resizes this component. T
		// draw border and padding on outside panel
		panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(10, 10, 10, 10)));
		queueLabel = new JLabel(); // create JLabel to show the queue
		queueLabel.setText( getQueueAll() ); // show the queue
		queueLabel.setVerticalAlignment( JLabel.TOP ); // set text alignment to top
		queueLabel.setOpaque( true ); // true if want to change background color
		queueLabel.setBackground(Color.YELLOW); // background color is white
		// draw border and padding on JLabel
		queueLabel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(10, 10, 10, 10)));
		panel.add(queueLabel, BorderLayout.CENTER);
		return panel;
	}

	/** 
	 * Compose JPanel to input string to enqueue
	 * @return JPanel to input string to enqueue
	 */
	private JPanel createInput() {
		JPanel panel = new JPanel(new BorderLayout()); // create base panel object
		// draw border and padding on outside panel
		panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(10, 10, 10, 10)));
		input = new JTextField(); // create input field to input queue
		panel.add(new JLabel("Input (space for separate): "), BorderLayout.WEST); // just add label
		panel.add(input, BorderLayout.CENTER);
		return panel;
	}

	/** 
	 * Compose JPanel for button collection
	 * @return JPanel for button collection
	 */
	private JPanel createButtons() {
		JPanel panel = new JPanel(new GridLayout( 1, 4 )); // create base panel object
		// draw border and padding on outside panel
		panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(10, 10, 10, 10)));
		JButton insertButton = new JButton( "EnQueue" );
		JButton deleteButton = new JButton( "DeQueue" );
		JButton progressButton = new JButton( "1,000,000 test" );
		JButton closeButton = new JButton( "Close" );
		insertButton.setPreferredSize(new Dimension(60, 40));
		deleteButton.setPreferredSize(new Dimension(60, 40));
		progressButton.setPreferredSize(new Dimension(60, 40));
		closeButton.setPreferredSize(new Dimension(60, 40));

		// insert text input to queue
		insertButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ( input.getText() != null && !input.getText().isEmpty() ) {
					String stay[] = input.getText().split(" ");
					for ( String text : stay ) {
						try {
							queue.enqueue( text );
						}  catch ( NullObjectException ex ) {
							Container frame = closeButton.getParent();
							JOptionPane.showMessageDialog(frame, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				queueLabel.setText( getQueueAll() );
			}
		});

		// remove a text from the queue
		deleteButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queue.dequeue();
				}  catch ( EmptyQueueException ex ) {
					Container frame = closeButton.getParent();
					JOptionPane.showMessageDialog(frame, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				queueLabel.setText( getQueueAll() );
			}
		});

		// run one million times' test
		progressButton.addActionListener( new ActionListener() {
			long startTime, endTime, oldTime;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// enqueue test
				Queue<Integer> testQ = new Queue<>();
				Random rand = new Random();
				
				SwingWorker<?, ?> prog1 = new SwingWorker<Integer, Integer>() {
			        @Override
			        protected Integer doInBackground() throws Exception {
			        	startTime = System.currentTimeMillis(); // set start time
			        	try {
							
							for ( int i=0; i < 1000000; i++ ) {
								int num = rand.nextInt( 100000 );
								testQ.enqueue( num );
								publish( i );
							}
						} catch ( NullObjectException ne ) {
							Container frame = closeButton.getParent();
							JOptionPane.showMessageDialog(frame, ne.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
			            return null;
			        }
			 
			        @Override
			        protected void process(List<Integer> val) {
			        	enBar.setValue(val.get(0));  
			        }
			        
			        @Override
			        protected void done() {
			        	enBar.setValue(1000000);
			        	endTime = System.currentTimeMillis() - startTime; 
			        	displayTime( endTime, enTime );
			        	SwingWorker<?, ?> prog2 = new SwingWorker<Integer, Integer>() {
					        @Override
					        protected Integer doInBackground() throws Exception {
					        	startTime = System.currentTimeMillis(); // set start time
					        	oldTime = 0;
					        	try {
					        		for ( int i=0; i < 1000000; i++ ) {
										testQ.dequeue();
										publish( i );
									}
					        	} catch ( EmptyQueueException ee ) {
									Container frame = closeButton.getParent();
									JOptionPane.showMessageDialog(frame, ee.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
								}
					            return null;
					        }
					 
					        @Override
					        protected void process(List<Integer> val) {
					        	deBar.setValue(val.get(0));  
					        	endTime = System.currentTimeMillis() - startTime;
					        	if ( oldTime != (endTime / 1000) % 60 ) {
					        		displayTime( endTime, deTime );
					        		oldTime = (endTime / 1000) % 60;
					        	}
					        }
					        
					        @Override
					        protected void done() {
					        	deBar.setValue(1000000);
					        	endTime = System.currentTimeMillis() - startTime; 
					        	displayTime( endTime, deTime );
					        }
					    };
					    prog2.execute();  
			        }
			    };
			    prog1.execute();  
			}
		});

		// the code that will terminate a program
		closeButton.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Container frame = closeButton.getParent();
				do 
					frame = frame.getParent(); 
				while (!(frame instanceof JFrame));                                      
				((JFrame) frame).dispose();
			}
		});

		panel.add(insertButton);
		panel.add(deleteButton);
		panel.add(progressButton);
		panel.add(closeButton);
		return panel;
	}

	/**
	 * Compose JPanel to show one million of Enqueue test
	 * @return JPanel to show one million of Enqueue test
	 */
	private JPanel createEnqueueProgress() {
		JPanel panel = new JPanel(new BorderLayout()); // create base panel object
		// draw border and padding on outside panel
		panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(10, 10, 10, 10)));
		enBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 1000000); // create enque progress to input queue
		enBar.setStringPainted(true);
		enBar.setForeground(Color.BLUE);
		enTime = new JLabel("  0:00:000");
		panel.add(new JLabel("Enqueue Progress: "), BorderLayout.WEST); // just add label
		panel.add(enBar, BorderLayout.CENTER);
		panel.add(enTime, BorderLayout.EAST);
		return panel;
	}

	/**
	 * Compose JPanel to show one million of Dequeue test
	 * @return JPanel to show one million of Dequeue test
	 */
	private JPanel createDequeueProgress() {
		JPanel panel = new JPanel(new BorderLayout()); // create base panel object
		// draw border and padding on outside panel
		panel.setBorder(new CompoundBorder(new EtchedBorder(EtchedBorder.LOWERED), new EmptyBorder(10, 10, 10, 10)));
		deBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 1000000); // create enque progress to input queue
		deBar.setStringPainted(true);
		deBar.setForeground(Color.BLACK);
		deTime = new JLabel("  0:00:000");
		panel.add(new JLabel("Dequeue Progress: "), BorderLayout.WEST); // just add label
		panel.add(deBar, BorderLayout.CENTER);
		panel.add(deTime, BorderLayout.EAST);
		return panel;
	}

	/**
	 * Return string in the queue
	 * @return string in the queue
	 */
	private String getQueueAll() {
		String str = "<html>";

		for (Iterator<String> iter = queue.iterator(); iter.hasNext(); ) {
			String e = iter.next();
			str += e + " ";
		}
		return str + "</html>";
	}

	/**
	 * Display the time elapsed
	 * @param msec millisecond
	 * @param lbl JLabel to display
	 */
	private void displayTime( long msec, JLabel lbl ) {
		long min = (msec / (1000*60)) % 60;
		long sec = (msec / 1000) % 60;
		lbl.setText( String.format(" %d:%02d:%03d", min, sec, msec - sec * 1000 - min*1000*60 ) );
	}
	
	/* ENTRY POINT for STAND-ALONE OPERATION	------------------------------------ the skeleton from Reginald Dyer */
	/**
	 * Entry point "main()" as required by the JVM. 
	 * @param args Standard command line parameters (arguments) as a string array.
	 */
	public static void main(String[] args) {
		QueueMain main = new QueueMain( args );
		main.initGUI();
	}

	/* ATTRIBUTES	---------------------------------------------------------------- the skeleton from Reginald Dyer */
	/** the attribute using as Queue object */
	private Queue<String> queue;
	/** the attribute using as JLabel object */
	private JLabel queueLabel;
	/** the attribute using as JTextField object */
	private JTextField input;
	/** the attribute to show progressing of enqueue test */
	private JProgressBar enBar;
	/** the attribute to show time of enqueue test */
	private JLabel enTime;
	/** the attribute to show progressing of dequeue test */
	private JProgressBar deBar;
	/** the attribute to show time of enqueue test */
	private JLabel deTime;

}