package additional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import dataLayer.Customer;
import dataLayer.Employee;

public class Generate {

	public static int getRandInt(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public static long getRandLong() {
		Random random = new Random();
		return random.nextLong();
	}

	public static long getRandCardNum() {
		return getRandLong();
	}

	public static int getRandMonth() {
		return getRandInt(1, 12);
	}

	public static int getRandDay() {
		return getRandInt(1, 31);
	}

	public static int getRandSecCode() {
		return getRandInt(100, 999);
	}

	public static String getRandFirstName() {
		int pos = getRandInt(0, Generate.firstNames.length);
		return firstNames[pos];
	}

	public static String getRandLastName() {
		int pos = getRandInt(0, Generate.lastNames.length);
		return Generate.lastNames[pos];
	}

	public static String getRandUserName() {
		int pos = getRandInt(0, Generate.userNames.length);
		return Generate.userNames[pos];
	}

	public static String getRandEmail() {
		int pos = getRandInt(0, Generate.emails.length);
		return Generate.emails[pos];
	}

	private static String getGenEmail(String first, String last) {
		return first.toLowerCase() + "." + last.toLowerCase() + "@gmail.com";
	}

	private static String getGenNumEmail(String first, String last) {
		return first.toLowerCase() + "." + last.toLowerCase() + Integer.toString(Generate.getRandInt(1, 100))
				+ "@gmail.com";
	}

	public static String getRandPassword() {
		int pos = getRandInt(0, Generate.passwords.length);
		return Generate.passwords[pos];
	}

	public static String getGenUserNameCus(String first, String last) {
		String result = first.substring(0, 3) + last.substring(0, 3) + "-cu";
		return result.toLowerCase();
	}

	public static String getGenUserNameCus(String first, String last, int num) {
		return getGenUserNameCus(first, last) + Integer.toString(num);
	}

	public static String getGenUserNameEmp(String first, String last) {
		String result = first.substring(0, 3) + last.substring(0, 3) + "-em";
		return result.toLowerCase();
	}

	public static String getGenUserNameEmp(String first, String last, int num) {
		return getGenUserNameEmp(first, last) + Integer.toString(num);
	}

	public static String getLoremIpsum() {
		return loremIpsum;
	}

	/*----------------------------------------------------------------------*/

	public static Collection<Customer> getRandCustomers() {
		return getRandCustomers(100000);
	}

	public static Collection<Customer> getRandCustomers(int count) {
		Collection<Customer> customers = new ArrayList<>();

		for (int i = 0; i < count; i++) {

			String first = getRandFirstName();
			String last = getRandLastName();
			String mail = getGenEmail(first, last);
			String username = getGenUserNameCus(first, last, i);
			String password = "heslo";

			Customer customer = new Customer(first, last, mail, username, password);

			customers.add(customer);
		}

		return customers;
	}

	/*----------------------------------------------------------------------*/

	public static Collection<Employee> getRandEmployee() {
		return getRandEmployees(100000);
	}

	public static Collection<Employee> getRandEmployees(int count) {
		Collection<Employee> employees = new ArrayList<>();

		for (int i = 0; i < count; i++) {

			String first = getRandFirstName();
			String last = getRandLastName();
			String mail = getGenEmail(first, last);
			String username = getGenUserNameEmp(first, last, i);
			String password = "heslo";
			String pos = "bezny_zamestnanec";
			int wage = 20000 + (getRandInt(1, 5) * 1000);

			Employee employee = new Employee(first, last, mail, username, password, pos, wage);

			employees.add(employee);
		}

		return employees;
	}

	/*----------------------------------------------------------------------*/

	private static final String[] firstNames = { "Maegan", "Filberte", "Jone", "Roselle", "Danita", "Rhett",
			"Granville", "Pietra", "Steward", "Donal", "Melony", "Alvira", "Vinny", "Donaugh", "Hakim", "Gearalt",
			"Hyman", "Lynn", "Grata", "Andree", "Charlena", "Dougie", "Kinny", "Sheilah", "Ula", "Maegan", "Camey",
			"Willy", "Barb", "Nikos", "Josefa", "Edward", "Nowell", "Agna", "Malinde", "Jory", "Wendi", "Alexio",
			"Mina", "Taite", "Maryl", "Benny", "Orton", "Wayland", "Tiffani", "Heidi", "Sapphira", "Quintin", "York",
			"Eleanora", "Car", "Genia", "Roseann", "Clyve", "Patty", "Vidovic", "Armando", "Win", "Alice", "Tillie",
			"Piggy", "Hobart", "Liana", "Euell", "Whittaker", "Christian", "Siouxie", "Derek", "Mandel", "Bambi",
			"Ainsley", "Hanni", "Janel", "Hermie", "Kerrin", "Candis", "Mauricio", "Osbourne", "Fabe", "Chris",
			"Bentlee", "Jennine", "Ransell", "Hayes", "Giulia", "Kayne", "Chaunce", "Sheila", "Brina", "Diane", "Gypsy",
			"Humfrey", "Oneida", "Nathalia", "Constantina", "Skylar", "Kriste", "Boigie", "Horton", "Jerrilee" };

	private static final String[] lastNames = { "Cruikshanks", "Cardoso", "Wanstall", "Philipard", "Climance",
			"Radclyffe", "Tregust", "Snyder", "Wermerling", "Bealton", "Goodee", "Watkiss", "Ritzman", "Coast",
			"Pilpovic", "Stribling", "Drennan", "Pietrzak", "Parffrey", "Ferneyhough", "Smellie", "MacDiarmid",
			"Slamaker", "Girault", "Nairne", "MacIlraith", "Coyte", "Allcock", "Kenewel", "Staveley", "Habbijam",
			"Saltman", "Joel", "Lammin", "Denyer", "Chasemore", "Herche", "Blennerhassett", "Durrans", "Nye", "Gaul",
			"Gilbart", "Attoc", "Rolfi", "Tesche", "Fonteyne", "Topling", "Dobbyn", "Pasley", "Heningham", "Jeavon",
			"Ubanks", "Kelf", "Kyme", "Pogosian", "Skilling", "Meechan", "Wooff", "Iggo", "Caroll", "Bazoge", "Luggar",
			"Colerick", "Matevosian", "McAne", "Burchett", "Garham", "Downs", "Wiskar", "Claussen", "Gantzman",
			"Brinkley", "Lightfoot", "Roggieri", "Weems", "Matteo", "Kemwall", "Stainland", "Tretter", "Kender",
			"MacScherie", "Tyreman", "Glassford", "Thairs", "Giacubo", "Hagley", "Winfindine", "Nussey", "Passion",
			"Harsnipe", "Niessen", "Fairclough", "Rookeby", "Brownfield", "Andrat", "Hickford", "Hinks", "Chinnock",
			"Bramble", "Trahearn" };

	private static final String[] userNames = { "mcruikshanks0", "fcardoso1", "jwanstall2", "rphilipard3", "dclimance4",
			"rradclyffe5", "gtregust6", "psnyder7", "swermerling8", "dbealton9", "mgoodeea", "awatkissb", "vritzmanc",
			"dcoastd", "hpilpovice", "gstriblingf", "hdrennang", "lpietrzakh", "gparffreyi", "aferneyhoughj",
			"csmelliek", "dmacdiarmidl", "kslamakerm", "sgiraultn", "unairneo", "mmacilraithp", "ccoyteq", "wallcockr",
			"bkenewels", "nstaveleyt", "jhabbijamu", "esaltmanv", "njoelw", "alamminx", "mdenyery", "jchasemorez",
			"wherche10", "ablennerhassett11", "mdurrans12", "tnye13", "mgaul14", "bgilbart15", "oattoc16", "wrolfi17",
			"ttesche18", "hfonteyne19", "stopling1a", "qdobbyn1b", "ypasley1c", "eheningham1d", "cjeavon1e",
			"gubanks1f", "rkelf1g", "ckyme1h", "ppogosian1i", "vskilling1j", "ameechan1k", "wwooff1l", "aiggo1m",
			"tcaroll1n", "pbazoge1o", "hluggar1p", "lcolerick1q", "ematevosian1r", "wmcane1s", "cburchett1t",
			"sgarham1u", "ddowns1v", "mwiskar1w", "bclaussen1x", "agantzman1y", "hbrinkley1z", "jlightfoot20",
			"hroggieri21", "kweems22", "cmatteo23", "mkemwall24", "ostainland25", "ftretter26", "ckender27",
			"bmacscherie28", "jtyreman29", "rglassford2a", "hthairs2b", "ggiacubo2c", "khagley2d", "cwinfindine2e",
			"snussey2f", "bpassion2g", "dharsnipe2h", "gniessen2i", "hfairclough2j", "orookeby2k", "nbrownfield2l",
			"candrat2m", "shickford2n", "khinks2o", "bchinnock2p", "hbramble2q", "jtrahearn2r" };

	private static final String[] emails = { "mcruikshanks0@netvibes.com", "fcardoso1@paypal.com",
			"jwanstall2@drupal.org", "rphilipard3@sakura.ne.jp", "dclimance4@flavors.me", "rradclyffe5@epa.gov",
			"gtregust6@wufoo.com", "psnyder7@bbb.org", "swermerling8@vk.com", "dbealton9@wikipedia.org",
			"mgoodeea@comcast.net", "awatkissb@godaddy.com", "vritzmanc@reference.com", "dcoastd@dropbox.com",
			"hpilpovice@ucoz.com", "gstriblingf@studiopress.com", "hdrennang@baidu.com", "lpietrzakh@unesco.org",
			"gparffreyi@cocolog-nifty.com", "aferneyhoughj@techcrunch.com", "csmelliek@ucoz.ru",
			"dmacdiarmidl@tripadvisor.com", "kslamakerm@printfriendly.com", "sgiraultn@hao123.com",
			"unairneo@csmonitor.com", "mmacilraithp@sourceforge.net", "ccoyteq@forbes.com", "wallcockr@furl.net",
			"bkenewels@illinois.edu", "nstaveleyt@nyu.edu", "jhabbijamu@toplist.cz", "esaltmanv@globo.com",
			"njoelw@msn.com", "alamminx@i2i.jp", "mdenyery@homestead.com", "jchasemorez@fda.gov", "wherche10@salon.com",
			"ablennerhassett11@miibeian.gov.cn", "mdurrans12@com.com", "tnye13@vkontakte.ru", "mgaul14@zimbio.com",
			"bgilbart15@weather.com", "oattoc16@usatoday.com", "wrolfi17@123-reg.co.uk", "ttesche18@xing.com",
			"hfonteyne19@w3.org", "stopling1a@ovh.net", "qdobbyn1b@pinterest.com", "ypasley1c@skype.com",
			"eheningham1d@vistaprint.com", "cjeavon1e@flavors.me", "gubanks1f@weibo.com", "rkelf1g@smh.com.au",
			"ckyme1h@nbcnews.com", "ppogosian1i@forbes.com", "vskilling1j@elegantthemes.com",
			"ameechan1k@thetimes.co.uk", "wwooff1l@jimdo.com", "aiggo1m@hhs.gov", "tcaroll1n@google.de",
			"pbazoge1o@cdbaby.com", "hluggar1p@surveymonkey.com", "lcolerick1q@shareasale.com",
			"ematevosian1r@dropbox.com", "wmcane1s@so-net.ne.jp", "cburchett1t@goodreads.com",
			"sgarham1u@kickstarter.com", "ddowns1v@unicef.org", "mwiskar1w@friendfeed.com", "bclaussen1x@tmall.com",
			"agantzman1y@ihg.com", "hbrinkley1z@ameblo.jp", "jlightfoot20@virginia.edu", "hroggieri21@soup.io",
			"kweems22@msu.edu", "cmatteo23@blogger.com", "mkemwall24@istockphoto.com", "ostainland25@csmonitor.com",
			"ftretter26@feedburner.com", "ckender27@csmonitor.com", "bmacscherie28@i2i.jp", "jtyreman29@lulu.com",
			"rglassford2a@google.co.jp", "hthairs2b@archive.org", "ggiacubo2c@java.com", "khagley2d@go.com",
			"cwinfindine2e@taobao.com", "snussey2f@homestead.com", "bpassion2g@time.com", "dharsnipe2h@nifty.com",
			"gniessen2i@npr.org", "hfairclough2j@sbwire.com", "orookeby2k@nih.gov", "nbrownfield2l@cpanel.net",
			"candrat2m@cdbaby.com", "shickford2n@multiply.com", "khinks2o@guardian.co.uk", "bchinnock2p@umn.edu",
			"hbramble2q@sbwire.com", "jtrahearn2r@yellowpages.com" };

	private static final String[] passwords = { "FrVmFVV", "Yup5DmM3f8", "yBQDId", "mZpaG9cnam9f", "Q2vVUXk1oxL",
			"sHKqRJpEBcQe", "24ijIgiEvX", "PaDTlCHWEuP", "necNoja4bo", "cev2Tt", "5p2GGNvu5Y", "N4c1Sl5N96",
			"tm7GiiIuMCO3", "TRzdLi2lI", "fdChhWO", "BhXbNoDdFZ", "6y12OgjnvGF", "Yr3zQqs", "LigVWNPOQetc", "p7BuYer",
			"dARU9Xd9zo", "Ag394u36S", "VgXGUaoWcmx", "Bszp4ayEc", "xIq5OTSE", "RQ5pppfa", "g0JGjRWd1", "aN9ZQ8cxI",
			"DKF2x5AWpz", "9MdUZC5H04JG", "vd8cGWp2qE", "abCvtsoy2", "qYVQSK", "NApLtX86QXcY", "V3SWJ1bzymH1",
			"dYQbhf8zGMd", "QAECQiKt2bZ", "QnhPo4H", "FmxL1OWPsu", "8UWFll", "iIZZ2ZQwdN", "ZMwpwE", "9beF2Ymcj",
			"vhsu0idY8", "WuZNVr0rrgj", "Yy66Kq4OnOm", "O5bFc0ZyFZSf", "71tJ5eikdRF", "ywMODHynm", "VJbJdI",
			"5eAHbYGwM", "UyVaYJ", "Rc8Ug21y", "QJv9RtvcJ4i", "OQcNnJxMKPWV", "EVGfEHoZyUIO", "4rlNeayn", "kFa0gwAVjKV",
			"1YMrcd", "5i6S6w", "o9MhEWb9X8", "5EVNBB5uTmD", "IgCcGQNxA", "sbDe6AJdBo", "b0I5CBCE0x", "nCmXWio",
			"PY3QAXVPJR", "IHmQB01ew4Ql", "deVWCO8HSoB", "DHmIPLXQrOh", "iggREu", "ibsqxvGkNKcD", "R55wzHm", "ATzEDFOE",
			"WqGN07Grw", "EusKnm", "Lt9slPFPA", "rk8N3095bm", "vqtPU2Vy4j", "AEC2Nx9K", "3rDnRV", "6DRycnYb",
			"yassVLpuQg", "PAIPnv", "i9dYmi59y", "teTuBw", "J8Dt60Fkj", "0JWcBpM", "gmRShO5TKE", "G5XQvnKDK",
			"mjUF78Uauk", "CXa84Z4F0a", "3vT8h5", "wTDSp2K5d2fM", "d6axGz6", "KaPr2oGbg", "ATe1HF3dW", "QgpUMBt",
			"lWESRB71", "Ull7Dl" };

	private static final String loremIpsum = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Nunc dapibus tortor vel mi dapibus sollicitudin. Aliquam ante. In laoreet, magna id viverra tincidunt, sem odio bibendum justo, vel imperdiet sapien wisi sed libero. Mauris dolor felis, sagittis at, luctus sed, aliquam non, tellus. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Curabitur bibendum justo non orci. Nulla non lectus sed nisl molestie malesuada. Vestibulum fermentum tortor id mi. Donec vitae arcu. Fusce consectetuer risus a nunc. Pellentesque pretium lectus id turpis. Vivamus ac leo pretium faucibus.";

}
