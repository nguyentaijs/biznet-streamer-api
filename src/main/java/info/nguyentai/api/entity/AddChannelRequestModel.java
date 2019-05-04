package info.nguyentai.api.entity;

public class AddChannelRequestModel {
	private String url;
	private String savePath;
	private String maintainDuration;
	private int segmentBufferCount;
	private String keepSavePath;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getMaintainDuration() {
		return maintainDuration;
	}

	public void setMaintainDuration(String maintainDuration) {
		this.maintainDuration = maintainDuration;
	}
	
	public int getSegmentBufferCount() {
		return segmentBufferCount;
	}

	public void setSegmentBufferCount(int segmentBufferCount) {
		this.segmentBufferCount = segmentBufferCount;
	}

	public String getKeepSavePath() {
		return keepSavePath;
	}

	public void setKeepSavePath(String keepSavePath) {
		this.keepSavePath = keepSavePath;
	}

	public AddChannelRequestModel(String url, String savePath, String maintainDuration, int segmentBufferCount,
			String keepSavePath) {
		super();
		this.url = url;
		this.savePath = savePath;
		this.maintainDuration = maintainDuration;
		this.segmentBufferCount = segmentBufferCount;
		this.keepSavePath = keepSavePath;
	}

	public AddChannelRequestModel() {
		super();
	}

}
