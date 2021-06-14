package test;

import dao.UserDataDao;

public class UserDataTest {
	public static void main(String[] args) {
		UserDataDao uDao = new UserDataDao();




		// selectP()のテスト
		System.out.println("---------- selectP()のテスト ----------");
		int pos = (int)uDao.selectP("Teacher1@email.jp");
		System.out.println("position：" + pos);



		/*
		// update()のテスト
		System.out.println("---------- update()のテスト ----------");
		Bc upRec = new Bc(2, "", "", "", "調査部", "部長", "", "", "", "", "", "");
		if (dao.update(upRec)) {
			System.out.println("更新成功！");
			List<Bc> cardList4 = dao.select(upRec);
			for (Bc card : cardList4) {
			System.out.println("id：" + card.getId());
			System.out.println("name：" + card.getName());
			System.out.println("name_kana：" + card.getName_kana());
			System.out.println("company:" + card.getCompany());
			System.out.println("department：" + card.getDepartment());
			System.out.println("position：" + card.getPosition());
			System.out.println("phone：" + card.getPhone());
			System.out.println("fax：" + card.getFax());
			System.out.println("mail：" + card.getMail());
			System.out.println("postal：" + card.getPostal());
			System.out.println("address：" + card.getAddress());
			System.out.println("remarks：" + card.getRemarks());
			}
		}
		else {
			System.out.println("更新失敗！");
		}
		*/



		/*
		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		if (dao.delete(1)) {
			System.out.println("削除成功！");
		}
		else {
			System.out.println("削除失敗！");
		}
		*/

	}
}