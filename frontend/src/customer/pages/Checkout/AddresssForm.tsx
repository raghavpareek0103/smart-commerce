import React from 'react';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import { useAppDispatch } from '../../../Redux Toolkit/Store';
import { createOrder } from '../../../Redux Toolkit/Customer/OrderSlice';
import type { Address } from '../../../types/userTypes';
import CloseIcon from '@mui/icons-material/Close';
import ArrowOutwardIcon from '@mui/icons-material/ArrowOutward';

// Validation schema
const ContactSchema = Yup.object().shape({
  name: Yup.string().required('Required'),
  mobile: Yup.string()
    .matches(/^[6-9]\d{9}$/, 'Invalid mobile number')
    .required('Required'),
  pinCode: Yup.string()
    .matches(/^\d{6}$/, 'Invalid pincode')
    .required('Required'),
  address: Yup.string().required('Required'),
  locality: Yup.string().required('Required'),
  city: Yup.string().required('Required'),
  state: Yup.string().required('Required'),
});

interface AddressFormProp {
  handleClose: () => void;
  paymentGateway: string
}

type FieldName = "name" | "mobile" | "pinCode" | "address" | "locality" | "city" | "state";
type Field = { name: FieldName; label: string; placeholder: string; half?: boolean; inputMode?: "numeric" | "text" };

const fields: Field[] = [
  { name: "name", label: "Full name", placeholder: "Raghav Pareek" },
  { name: "mobile", label: "Mobile", placeholder: "10-digit number", half: true, inputMode: "numeric" },
  { name: "pinCode", label: "Pin code", placeholder: "6 digits", half: true, inputMode: "numeric" },
  { name: "address", label: "Address", placeholder: "House no, building, street" },
  { name: "locality", label: "Locality / Town", placeholder: "Area or landmark" },
  { name: "city", label: "City", placeholder: "Jaipur", half: true },
  { name: "state", label: "State", placeholder: "Rajasthan", half: true },
];

const AddressForm: React.FC<AddressFormProp> = ({ handleClose, paymentGateway }) => {
  const dispatch = useAppDispatch()
  const formik = useFormik({
    initialValues: {
      name: '',
      mobile: '',
      pinCode: '',
      address: '',
      locality: '',
      city: '',
      state: '',
    },
    validationSchema: ContactSchema,
    onSubmit: (values) => {
      handleCreateOrder(values as Address);
      handleClose();
    },
  });

  const handleCreateOrder = (address: Address) => {
    dispatch(createOrder({ address, jwt: localStorage.getItem('jwt') || "", paymentGateway }))
  }

  return (
    <div className='text-[#2a1d17]'>
      {/* heading */}
      <div className='flex items-start justify-between gap-4 pb-6'>
        <div>
          <div className='flex items-center gap-3 text-[11px] font-semibold tracking-[0.35em] uppercase text-[#b4532a]'>
            <span className='w-8 h-px bg-[#b4532a]' /> Delivery
          </div>
          <h2 className='mt-2 text-3xl font-semibold' style={{ fontFamily: "'Playfair Display', Georgia, serif" }}>
            New <span className='italic font-medium text-[#b4532a]'>address</span>
          </h2>
        </div>
        <button
          type='button'
          onClick={handleClose}
          aria-label='Close'
          className='w-10 h-10 shrink-0 rounded-full grid place-items-center border border-[#2a1d17]/15 hover:bg-[#2a1d17] hover:text-[#f6f1ea] transition-colors'
        >
          <CloseIcon sx={{ fontSize: 18 }} />
        </button>
      </div>

      <form onSubmit={formik.handleSubmit} noValidate>
        <div className='grid grid-cols-2 gap-4'>
          {fields.map((f) => {
            const error = formik.touched[f.name] && formik.errors[f.name];
            return (
              <label key={f.name} className={f.half ? "col-span-1" : "col-span-2"}>
                <span className='text-[10px] font-semibold tracking-[0.25em] uppercase text-[#2a1d17]/55'>{f.label}</span>
                <input
                  name={f.name}
                  value={formik.values[f.name]}
                  onChange={formik.handleChange}
                  onBlur={formik.handleBlur}
                  placeholder={f.placeholder}
                  inputMode={f.inputMode}
                  className={`mt-1.5 w-full h-12 px-4 rounded-2xl bg-[#fbf8f4] border outline-none text-[15px] transition-all placeholder:text-[#2a1d17]/30 focus:border-[#b4532a] focus:shadow-[0_0_0_4px_rgba(180,83,42,0.12)] ${error ? "border-[#b4532a]" : "border-[#2a1d17]/15"}`}
                />
                {error && <span className='mt-1 block text-xs text-[#b4532a]'>{error as string}</span>}
              </label>
            );
          })}
        </div>

        <button
          type="submit"
          className='group mt-7 w-full flex items-center justify-between h-14 pl-7 pr-2 rounded-full bg-[#2a1d17] hover:bg-[#1b2420] text-[#f6f1ea] text-sm font-semibold tracking-[0.15em] uppercase transition-colors'
        >
          Deliver here & pay
          <span className='w-10 h-10 rounded-full grid place-items-center bg-[#b4532a] transition-transform duration-300 group-hover:rotate-45'>
            <ArrowOutwardIcon sx={{ fontSize: 18 }} />
          </span>
        </button>
        <p className='mt-3 text-center text-xs text-[#2a1d17]/45'>This address will be saved and you'll be taken to secure payment.</p>
      </form>
    </div>
  );
};

export default AddressForm;