package jp.co.internous.team2501.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.team2501.model.domain.TblCart;
import jp.co.internous.team2501.model.domain.dto.CartDto;
import jp.co.internous.team2501.model.form.CartForm;
import jp.co.internous.team2501.model.mapper.TblCartMapper;
import jp.co.internous.team2501.model.session.LoginSession;

/**
 * カート情報に関する処理のコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2501/cart")
public class CartController {

	@Autowired
	private TblCartMapper cartMapper;

	@Autowired
	private LoginSession loginSession;

	private Gson gson = new Gson();

	/**
	 * カート画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/")
	public String index(Model m) {

		int userId = loginSession.isLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();

		List<CartDto> carts = cartMapper.findByUserId(userId);

		m.addAttribute("loginSession", loginSession);
		m.addAttribute("carts", carts);
		
		return "cart";
	}

	/**
	 * カートに追加処理を行う
	 * @param f カート情報のForm
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {

		int userId = loginSession.isLogined() ? loginSession.getUserId() : loginSession.getTmpUserId();

		f.setUserId(userId);

		TblCart cart = new TblCart(f);
		int result = 0;
		if (cartMapper.findCountByUserIdAndProductId(userId, f.getProductId()) > 0) {
			result = cartMapper.update(cart);
		} else {
			result = cartMapper.insert(cart);
		}
		if (result > 0) {
			List<CartDto> carts = cartMapper.findByUserId(userId);

			m.addAttribute("loginSession", loginSession);
			m.addAttribute("carts", carts);
		}
		
		return "cart";
	}

	/**
	 * カート情報を削除する
	 * @param checkedIdList 選択したカート情報のIDリスト
	 * @return true:削除成功、false:削除失敗
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		int result = 0;
		
		CartForm form = gson.fromJson(checkedIdList, CartForm.class);
		
		result = cartMapper.deleteById(form.getCheckedIdList());
		
		return result == form.getCheckedIdList().size();
	}
}