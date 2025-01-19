# LCWaikiki Test Otomasyon Projesi

## Proje Hakkında
Bu proje, LCWaikiki e-ticaret platformunun belirli işlevlerini otomatikleştirerek manuel test yükünü azaltmayı hedefleyen bir Selenium tabanlı test otomasyon çözümüdür. Proje, kullanıcı deneyimini ve site fonksiyonlarını detaylı şekilde test etmek için oluşturulmuştur.

## Kullanılan Teknolojiler
- **Programlama Dili:** Java
- **Test Çerçevesi:** TestNG
- **Otomasyon Araçları:** Selenium WebDriver
- **Raporlama:** Allure
- **Tasarım Deseni:** Page Object Model (POM)

## Proje Mimarisi
Proje, işlevsellik ve sürdürülebilirlik göz önünde bulundurularak modüler bir yapıda tasarlanmıştır. Aşağıdaki paketlerden oluşmaktadır:

### 1. Pages Paketi
Bu paket, her sayfa için ayrı bir sınıf içerecek şekilde organize edilmiştir. Bu sınıflar, sayfalara özgü işlem ve elementleri kapsar:

- **BasePage:** Diğer sayfaların temel aldığı, genel fonksiyonları ve WebDriver ayarlarını barındıran sınıf.
- **HomePage:** Ana sayfa üzerinde gerçekleştirilen işlemleri içerir.
- **LoginPage:** Kullanıcı giriş işlemleri için yöntemler sunar (örneğin, giriş butonuna tıklama, e-posta ve şifre girme).
- **PaymentPage:** Ödeme ekranındaki işlemleri yönetir (örneğin, ödeme bilgilerini doğrulama).
- **ProductDetailPage:** Ürün detaylarının kontrol edildiği işlemleri içerir (örneğin, ürün bilgisini alma, sepete ekleme).
- **SearchResultPage:** Arama sonuçlarında gezinme ve filtreleme işlemlerini içerir.

### 2. Tests Paketi
Test senaryoları, her bir sayfa veya işlev için ayrı sınıflara ayrılmıştır:

- **BaseTest:** 
  - **setUp:** Her test öncesinde çalışarak WebDriver kurulumunu yapar.
  - **tearDown:** Testler tamamlandıktan sonra WebDriver oturumunu kapatır ve temizleme işlemlerini gerçekleştirir.
- **LoginPageTest:** Giriş işlemlerini doğrulayan testleri içerir.
- **PaymentPageTest:** Ödeme adımlarını test eder.
- **ProductDetailPageTest:** Ürün detaylarıyla ilgili işlemlerin doğru şekilde çalıştığını kontrol eder.
- **SearchResultPageTest:** Ürün arama ve filtreleme senaryolarını test eder.

## Test Senaryoları

### 1. Kullanıcı Girişi
- Geçerli kullanıcı bilgileriyle giriş işlemini test eder.
- Giriş sonrası beklenen URL veya kullanıcı arayüzünü doğrular.

### 2. Ürün Arama ve Filtreleme
- Kullanıcının belirli kriterlere göre ürün aramasını doğrular.
- Arama sonuçlarında filtreleme ve sıralama işlemlerinin doğru çalıştığını kontrol eder.

### 3. Ürün Detay Kontrolü
- Seçilen ürünün ad, fiyat, renk gibi detaylarını doğrular.
- Ürünü sepete ekler ve sepetteki bilgilerin doğruluğunu kontrol eder.

### 4. Ödeme İşlemleri
- Sepet işlemlerinin ardından ödeme adımlarını test eder.
- Ödeme ekranındaki bilgilerin tutarlılığını doğrular.

## Öne Çıkan Özellikler
- **Page Object Model (POM):** Proje yapısının sürdürülebilir ve okunabilir olmasını sağlar.
- **Dinamik ve Çeşitli Senaryolar:** Kullanıcıların e-ticaret deneyimini simüle eden kapsamlı testler.
- **Allure Raporlama:** Detaylı ve görsel test raporları ile sonuçların daha anlaşılır hale getirilmesi.

## Nasıl Çalıştırılır?
1. Bu depoyu yerel makinenize klonlayın.
2. Projeyi tercih ettiğiniz bir IDE’ye (örneğin IntelliJ IDEA) aktarın.
3. **pom.xml** dosyasındaki bağımlılıkları yükleyin.
4. Test sınıflarını TestNG kullanarak çalıştırın.
5. Test sonuçlarını Allure raporları aracılığıyla görüntüleyin.

Projemi incelediğiniz için teşekkür ederim.
Mert Musabeşeoğlu

