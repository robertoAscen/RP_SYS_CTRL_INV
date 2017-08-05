/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eneri.scstock.Herramientas;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author RAscencio
 */
public class Autocomplete implements DocumentListener
{
    
  private JTextField textField;
  public final List<String> keywords;
  
  private static enum Mode
  {
    INSERT,  COMPLETION;
  }
  
  private Mode mode = Mode.INSERT;
  
  public Autocomplete(JTextField textField, List<String> keywords)
  {
    this.textField = textField;
    this.keywords = keywords;
    Collections.sort(keywords);
  }
  
  public void changedUpdate(DocumentEvent ev) {}
  
  public void removeUpdate(DocumentEvent ev) {}
  
  public void insertUpdate(DocumentEvent ev)
  {
    if (ev.getLength() != 1) {
      return;
    }
    int pos = ev.getOffset();
    int w;
    String content = null;
    try
    {
      content = this.textField.getText(0, pos + 1);
    }
    catch (BadLocationException e)
    {
      e.printStackTrace();
    }
    for (w = pos; w >= 0; w--) 
    {
      if (!Character.isLetter(content.charAt(w))) 
      {
        break;
      }
    }
    if (pos - w < 2)
    {
      return;
    }
    String prefix = content.substring(w + 1).toLowerCase();
    int n = Collections.binarySearch(this.keywords, prefix);
    if ((n < 0) && (-n <= this.keywords.size()))
    {
      String match = (String)this.keywords.get(-n - 1);
      if (match.startsWith(prefix))
      {
        String completion = match.substring(pos - w);
        
        SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
      }
    }
    else
    {
      this.mode = Mode.INSERT;
    }
  }
  
  public class CommitAction
    extends AbstractAction
  {
    private static final long serialVersionUID = 5794543109646743416L;
    
    public CommitAction() {}
    
    public void actionPerformed(ActionEvent ev)
    {
      if (Autocomplete.this.mode == Autocomplete.Mode.COMPLETION)
      {
        int pos = Autocomplete.this.textField.getSelectionEnd();
        StringBuffer sb = new StringBuffer(Autocomplete.this.textField.getText());
        sb.insert(pos, " ");
        Autocomplete.this.textField.setText(sb.toString());
        Autocomplete.this.textField.setCaretPosition(pos + 1);
        Autocomplete.this.mode = Autocomplete.Mode.INSERT;
      }
      else
      {
        Autocomplete.this.textField.replaceSelection("\t");
      }
    }
  }
  
  private class CompletionTask
    implements Runnable
  {
    private String completion;
    private int position;
    
    CompletionTask(String completion, int position)
    {
      this.completion = completion;
      this.position = position;
    }
    
    public void run()
    {
      StringBuffer sb = new StringBuffer(Autocomplete.this.textField.getText());
      sb.insert(this.position, this.completion);
      Autocomplete.this.textField.setText(sb.toString());
      Autocomplete.this.textField.setCaretPosition(this.position + this.completion.length());
      Autocomplete.this.textField.moveCaretPosition(this.position);
      Autocomplete.this.mode = Autocomplete.Mode.COMPLETION;
    }
  }    
}
