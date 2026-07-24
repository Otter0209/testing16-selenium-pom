package pages;

import org.openqa.selenium.By;

public class hrmAdminPage {


    // Link "Admin" trên Sidebar
    By adminSidebarLink = By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");

    // Link "PIM" trên Sidebar
    By pimSidebarLink = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");

    // Link "Leave" trên Sidebar
    By leaveSidebarLink = By.xpath("//a[@href='/web/index.php/leave/viewLeaveModule']");


    By usernameInput = By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input");

    // Dropdown "User Role" (chua bam mo)
    By userRoleDropdown = By.xpath("//label[text()='User Role']/ancestor::div[contains(@class, 'oxd-input-group')]//div[contains(@class, 'oxd-select-text')]");
    // Option "Admin" trong dropdown User Role (sau khi da bam mo dropdown)
    By roleOptionAdmin = By.xpath("//div[@role='option']//span[text()='Admin']");

    // Option "ESS" trong dropdown User Role (sau khi da bam mo dropdown)
    By roleOptionESS = By.xpath("//div[@role='option']//span[text()='ESS']");


    // Cách 1: An toàn nhất (Tìm label "Employee Name" rồi trỏ xuống thẻ input bên trong)
    By employeeNameInput = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div//input");

    By resetButton = By.xpath("//button[normalize-space()='Reset']");

    By searchButtonText = By.xpath("//button[normalize-space()='Search']");

     By userManagementTab = By.xpath("//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and contains(., 'User Management')]");


    // Lấy ô "Admin" đầu tiên xuất hiện trong bảng (duy nhất)
    // Tìm thẻ div chứa chữ Admin nằm ở cột thứ 2 (của dòng đầu tiên tìm thấy)
    By adminUsernameCell = By.xpath("(//div[@role='cell'][2]//div[text()='Admin'])[1]");

    By adminUserRoleCell = By.xpath("(//div[@role='cell'][3]//div[text()='Admin'])[1]");


    By deleteIconFirstRow = By.xpath("(//i[contains(@class, 'bi-trash')])[1]");

    By editIconFirstRow = By.xpath("(//i[contains(@class, 'bi-pencil-fill')])[1]");

    By userRoleColumnHeader = By.xpath("//div[@role='columnheader' and contains(., 'User Role')]");

    By employeeNameColumnHeader = By.xpath("//div[@role='columnheader' and contains(., 'Employee Name')]");

    By breadcrumbAdmin = By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-module') and text()='Admin']");
    By breadcrumbUserManagement = By.xpath("//h6[contains(@class, 'oxd-topbar-header-breadcrumb-level') and text()='User Management']");


    By chevronLeftIcon = By.xpath("//i[contains(@class, 'bi-chevron-left')]");


    By caretUpIcon = By.xpath("//i[contains(@class, 'bi-caret-up-fill')]");
    By addButton = By.xpath("//button[normalize-space()='Add']");
}