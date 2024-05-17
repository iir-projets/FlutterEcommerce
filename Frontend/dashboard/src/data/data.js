import React from "react";

// const backendUrl = 'http://192.168.56.1:8081';

export const ProductImage = (props) => (
  <div>
      <img
          className="rounded-xl h-20 md:ml-3"
          src={props.image}
          alt={props.name}
      />
  </div>
);
export const ProductGrid = [
    {
      field:"image",
      headerText: "image",
      template: ProductImage,
      textAlign: "Center",
      width: "120",
    },
    {
      field: "description",
      headerText: "description",
      width: "150",
      editType: "dropdownedit",
      textAlign: "Center",
    },
    {
      field: "name",
      headerText: "name",
      width: "150",
      textAlign: "Center",
    },
    {
      field: "price",
      headerText: "price",
      format: "C2",
      textAlign: "Center",
      editType: "numericedit",
      width: "150",
    },
    {
      field: "quantite",
      headerText: "quantite",
      format: "C2",
      textAlign: "Center",
      editType: "numericedit",
      width: "150",
    },
    {
      field: "categorie.catNom",
      headerText: "categorie",
      format: "C2",
      textAlign: "Center",
      editType: "numericedit",
      width: "150",
    }

  ];
  export const contextMenuItems = [
    "AutoFit",
    "AutoFitAll",
    "SortAscending",
    "SortDescending",
    "Copy",
    "Edit",
    "Delete",
    "Save",
    "Cancel",
    "PdfExport",
    "ExcelExport",
    "CsvExport",
    "FirstPage",
    "PrevPage",
    "LastPage",
    "NextPage",
  ];

  export const earningData = [
    {
      // icon: <MdOutlineSupervisorAccount />,
      amount: "39,354",
      percentage: "-4%",
      title: "Customers",
      iconColor: "#03C9D7",
      iconBg: "#E5FAFB",
      pcColor: "red-600",
    },
  ];
  const gridEmployeeProfile = (props) => (
    <div className="flex items-center gap-2">
      <img
        className="rounded-full w-10 h-10"
        src={props.EmployeeImage}
        alt="employee"
      />
      <p>{props.Name}</p>
    </div>
  );
  export const employeesGrid = [
    {
      headerText: "image",
      width: "150",
      template: gridEmployeeProfile,
      textAlign: "Center",
    },
    {
      field: "name",
      headerText: "Name",
      width: "170",
      textAlign: "Center",
    },
  ];
  export const employeesData = [
    {
      EmployeeID: 1,
      name: "shoes",
      Namkje: "Sales Representative",
      HireDate: "01/02/2021",
      Country: "USA",
      ReportsTo: "Carson",
    },
    {
      EmployeeID: 2,
      name: "bags",
      Title: "Marketing Head",
      HireDate: "01/02/2021",
      Country: "USA",
      ReportsTo: "Carson",
    },
    {
      EmployeeID: 3,
      name: "perfums",
      Title: "HR",
      HireDate: "01/02/2021",
      Country: "USA",
      ReportsTo: "Carson",
    },
  ];
  export const customersData = [
    {
      CustomerID: 1,
      CustomerName: "wiam e",
      CustomerEmail: "wiam@gmail.com",
      phonenumber: "0625894758",
      orders: "40",
    },
    {
      CustomerID: 2,
      CustomerName: "Safaa el",
      CustomerEmail: "safaa@gmail.com",
      phonenumber: " 061547858",
      orders: "20",
    },
    {
      CustomerID: 3,
      CustomerName: "karim aitali",
      CustomerEmail: "karim@gmail.com",
      phonenumber: "06124578",
      orders: "12",
    },
  ];export const customersGrid = [
    { type: "checkbox", width: "50" },
    {
      field: "nom",
      headerText: "last name",
      width: "120",
      textAlign: "Center",
      isPrimaryKey: true,
    },
    {
      field: "prenom",
      headerText: "first name",
      width: "150",
      textAlign: "Center",
    },
    {
      field: "email",
      headerText: "email",
      width: "130",
      format: "yMd",
      textAlign: "Center",
    },
    {
      field: "address",
      headerText: "address",
      width: "100",
      format: "C2",
      textAlign: "Center",
    },
    {
      field: "telephone",
      headerText: "phone number",
      width: "150",
      textAlign: "Center",
    },
  ];
