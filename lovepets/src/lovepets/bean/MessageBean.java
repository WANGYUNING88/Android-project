package lovepets.bean;

public class MessageBean {

    private int message_id;
    private String message_content;
    private int send_id;
    private int receive_id;
    
    public MessageBean(int message_id,String message_content,int send_id,int receive_id) {
    	this.message_id = message_id;
    	this.message_content = message_content;
    	this.send_id = send_id;
    	this.receive_id = receive_id;
    }
    public MessageBean(String message_content,int send_id,int receive_id) {
    
    	this.message_content = message_content;
    	this.send_id = send_id;
    	this.receive_id = receive_id;
    }
    
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public int getSend_id() {
		return send_id;
	}
	public void setSend_id(int send_id) {
		this.send_id = send_id;
	}
	public int getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(int receive_id) {
		this.receive_id = receive_id;
	}


}
