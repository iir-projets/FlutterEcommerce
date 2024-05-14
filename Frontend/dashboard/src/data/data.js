import React from "react";

const backendUrl = 'http://192.168.56.1:8081';

export const productImage = (props) => (
    <div>
      <img
        className="rounded-xl h-20 md:ml-3"
        src={`${backendUrl}/images/${props.image}`} // Using string interpolation
        alt={props.name}
      />
    </div>
  );
export const ProductGrid = [
    {
      field:"image",
      headerText: "image",
      template: productImage,
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
