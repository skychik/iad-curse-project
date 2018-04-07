//package ru.ifmo.cs.iad.iadcurseproject.entity.custom;
//
//import javax.persistence.*;
//
//@SqlResultSetMapping(name="NewsPreview",
//		entities={
//				@EntityResult(entityClass=NewsPreview.class, fields={
//						@FieldResult(name="id", column="id"),
//						@FieldResult(name="authorLogin", column="authorLogin"),
//						@FieldResult(name="contentPreview", column="contentPreview"),
//						@FieldResult(name="loopsNumber", column="loopsNumber"),
//						@FieldResult(name="poopsNumber", column="poopsNumber"),
//						@FieldResult(name="commentsNumber", column="commentsNumber"),
//						@FieldResult(name="repostsNumber", column="repostsNumber"),
//						@FieldResult(name="creationDate", column="creationDate"),
//						@FieldResult(name="alteringDate", column="alteringDate")})},
//		columns={
//				@ColumnResult(name="item_name")}
//)
////@Entity
//@NamedNativeQueries(value = {
//		@NamedNativeQuery(name = "NewsPreview.findAllNewsPreviewsSortedByAlteringDate",
//				query = "SELECT u.login AS authorLogin, n.content_preview AS contentPreview, count(lop) AS loopsNumber, " +
//						"count(pop) AS poopsNumber, count(com) AS commentsNumber, count(rep) AS repostsNumber " +
//						"FROM public.news n, public.news_loop lop, public.news_poop pop, public.comment com , " +
//						"public.repost rep, public.user u " +
//						"WHERE u.id = n.id_user AND lop.id_news = n.id AND pop.id_news = n.id AND " +
//						"com.id_news = n.id AND n.id IN (" +
//						"SELECT rep.id_news " +
//						"FROM public.repost rep, public.subscription sub " +
//						"WHERE rep.id_user = sub.id_on_whom " +
//						"ORDER BY n.altering_date" +
//						")",
//				resultClass = NewsPreview.class),
//		@NamedNativeQuery(name = "NewsPreview.findAllNewsPreviewsForUserByUserIdSortedByAlteringDate",
//				query = "SELECT u.login AS authorLogin, n.content_preview AS contentPreview, count(lop) AS loopsNumber, " +
//						"count(pop) AS poopsNumber, count(com) AS commentsNumber, count(rep) AS repostsNumber " +
//						"FROM public.news n, public.news_loop lop, public.news_poop pop, public.comment com , " +
//						"public.repost rep, public.user u " +
//						"WHERE u.id = n.id_user AND lop.id_news = n.id AND pop.id_news = n.id AND " +
//						"com.id_news = n.id AND n.id IN (" +
//						"SELECT rep.id_news " +
//						"FROM public.repost rep, public.subscription sub " +
//						"WHERE rep.id_user = sub.id_on_whom AND sub.id_who = ? " +
//						"ORDER BY n.altering_date" +
//						")",
//				resultClass = NewsPreview.class)
//})
//public /*class*/interface NewsPreview {
////	@Id
////	private Long id;
////	private String authorLogin;
////	private String contentPreview;
////	private Long loopsNumber;
////	private Long poopsNumber;
////	private Long commentsNumber;
////	private Long repostsNumber;
////	private Long creationDate;
////	private Long alteringDate;
//
//	/*public*/ Long getId(); /*{
//		return id;
//	}*/
////	public void setId(Long id) {
////		this.id = id;
////	}
//	/*public*/ String getAuthorLogin(); /*{
//		return authorLogin;
//	}*/
////	public void setAuthorLogin(String authorLogin) {
////		this.authorLogin = authorLogin;
////	}
//	/*public*/ String getContent(); /*{
//		return contentPreview;
//	}*/
////	public void setContentPreview(String contentPreview) {
////		this.contentPreview = contentPreview;
////	}
//	/*public*/ Long getLoopsNumber(); /*{
//		return loopsNumber;
//	}*/
////	public void setLoopsNumber(Long loopsNumber) {
////		this.loopsNumber = loopsNumber;
////	}
//	/*public*/ Long getPoopsNumber(); /*{
//		return poopsNumber;
//	}*/
////	public void setPoopsNumber(Long poopsNumber) {
////		this.poopsNumber = poopsNumber;
////	}
//	/*public*/ Long getCommentsNumber(); /*{
//		return commentsNumber;
//	}*/
////	public void setCommentsNumber(Long commentsNumber) {
////		this.commentsNumber = commentsNumber;
////	}
//	/*public*/ Long getRepostsNumber(); /*{
//		return repostsNumber;
//	}*/
////	public void setRepostsNumber(Long repostsNumber) {
////		this.repostsNumber = repostsNumber;
////	}
//	/*public*/ Long getCreationDate(); /*{
//		return creationDate;
//	}*/
////	public void setCreationDate(Long creationDate) {
////		this.creationDate = creationDate;
////	}
//	/*public*/ Long getAlteringDate(); /*{
//		return alteringDate;
//	}*/
////	public void setAlteringDate(Long alteringDate) {
////		this.alteringDate = alteringDate;
////	}
//}
