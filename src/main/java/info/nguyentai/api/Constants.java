package info.nguyentai.api;

public class Constants {
	public enum VodStatus {
		DELETED("Deleted"), UPLOADING("Uploading");
		private String requestCode;
		
		VodStatus(String requestCode) {
			this.requestCode = requestCode;
		}
		
		public String getRequestCode() {
			return this.requestCode;
		}
		
		public VodStatus getStreamStatusFromCode(String code) {
			if (code.equalsIgnoreCase(VodStatus.UPLOADING.requestCode)) {
				return VodStatus.UPLOADING;
			} else if (code.equalsIgnoreCase(VodStatus.DELETED.requestCode)) {
				return VodStatus.DELETED;
			} else {
				return VodStatus.UPLOADING;
			}
		}
	}
}
