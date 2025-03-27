package jp.co.internous.team2501.model.form;


import java.io.Serializable;
import java.util.List;

/**
 * カートフォーム
 * @author インターノウス
 *
 */
public class CartForm implements Serializable{
	private static final long serialVersionUID = 1L;
	private int userId;
	private int productId;
	private int productCount;
	
	private List<Integer> checkedIdList;

	/**
	 * ユーザーIDを取得する
	 * @return ユーザーID
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * ユーザーIDを設定する
	 * @param userId ユーザーID
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	/**
	 * 商品IDを取得する
	 * @return 商品ID
	 */
	public int getProductId() {
		return productId;
	}
	
	/**
	 * 商品IDを設定する
	 * @param productId 商品ID
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	/**
	 * 個数を取得する
	 * @return 個数
	 */
	public int getProductCount() {
		return productCount;
	}
	
	/**
	 * 個数を設定する
	 * @param productCount 個数
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	/**
	 * チェック済IDのリストを取得する
	 * @return チェック済IDのリスト
	 */
	public List<Integer> getCheckedIdList() {
		return checkedIdList;
	}

	/**
	 * チェック済IDのリストを設定する
	 * @param checkedIdList checkedIdList
	 */
	public void setCheckedIdList(List<Integer> checkedIdList) {
		this.checkedIdList = checkedIdList;
	}

}
