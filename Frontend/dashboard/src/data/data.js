import React from "react";


export const gridOrderImage = (props) => (
    <div>
      <img
        className="rounded-xl h-20 md:ml-3"
        src={`./assets/products/${props.image_path}`} // Using string interpolation
        alt={props.name}
      />
    </div>
  );
export const ordersGrid = [
    {
      field:"image_path",
      headerText: "image",
      template: gridOrderImage,
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