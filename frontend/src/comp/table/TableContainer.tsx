import { IonCol, IonGrid, IonInput, IonRow } from '@ionic/react';
import './TableContainer.css';
import TableRow from './TableRow';
class additional_col{
  label!: string;
  column!: string;
  link!: string;
  params!: string[];

  
  
}
class header{
  label?: string;
  col?: string;
}
interface ContainerProps {
  header?: header[];
  data?: object[];
  obj?: object;
  additional_column?: additional_col[];
}

const TableContainer: React.FC<ContainerProps> = (props) => {
  var header:any=props.header;
  return (
      <IonGrid class='table'>

        <IonRow>
          {header?.map((element:any,index:any)=>{
            return(<IonCol class='header' key={index}>{element.label}</IonCol>);
          })}
          {props.additional_column?.map((element,index)=>{
            return(<IonCol class='header' key={index}>{element.label}</IonCol>);
          })}
        </IonRow>

        {props.data?.map((element,index)=>{
          return (<TableRow header={header} key={index} object={element} additional_col={props.additional_column} />);
        })}

      </IonGrid>

  );
};

export default TableContainer;
