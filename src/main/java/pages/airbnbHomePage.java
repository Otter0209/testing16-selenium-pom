package pages;

import org.openqa.selenium.By;

public class airbnbHomePage {

    // 1. Logo CyberSoft ở góc trái
    By loc1_Logo = By.xpath("//img[@alt='Cyber Logo']");

    // 2. Menu Home
    By loc2_MenuHome = By.xpath("//a[normalize-space()='Home']");

    // 3. Menu About
    By loc3_MenuAbout = By.xpath("//a[normalize-space()='About']");

    // 4. Icon Avatar người dùng ở góc phải
    By loc4_Avatar = By.xpath("//img[contains(@src, '6596121.png')]");

    // 5. Khối nhập "Địa điểm" trên thanh tìm kiếm
    By loc5_LocationBox = By.xpath("//p[text()='Địa điểm']/parent::div");

    // 6. Khối hiển thị khoảng Ngày tháng (Lịch) trên thanh tìm kiếm
    By loc6_DateRangeBox = By.xpath("//div[contains(@class, 'col-span-4')]");

    // 7. Nút Tìm kiếm (kính lúp màu đỏ)
    By loc7_SearchButton = By.xpath("//div[contains(@class, 'bg-main')]");

    // 8. Nút Dấu Cộng (+) tăng khách
    By loc8_IncreaseGuest = By.xpath("//button[div[text()='+']]");

    // 9. Nút Dấu Trừ (-) giảm khách
    By loc9_DecreaseGuest = By.xpath("//button[div[text()='-']]");

    // 10. Toàn bộ khối gợi ý "Hồ Chí Minh" (Bao gồm hình ảnh và text)
    By loc10_HcmBlock = By.xpath("//h2[text()='Hồ Chí Minh']/ancestor::div[contains(@class, 'flex items-center gap-3')]");

    // 11. Dòng chữ "Cần Thơ" trong khối gợi ý
    By loc11_CanThoText = By.xpath("//h2[text()='Cần Thơ']");

    // 12. Nút bộ lọc "Loại nơi ở"
    By loc12_FilterPropertyType = By.xpath("//button[normalize-space()='Loại nơi ở']");

    // 13. Nút bộ lọc "Giá"
    By loc13_FilterPrice = By.xpath("//button[normalize-space()='Giá']");

    // 14. Đoạn text "6.5 giờ lái xe" của mục Nha Trang
    By loc14_DrivingTime = By.xpath("//h2[text()='Nha Trang']/following-sibling::p");

}