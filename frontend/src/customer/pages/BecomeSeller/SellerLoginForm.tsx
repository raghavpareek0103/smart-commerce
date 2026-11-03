import { createSlice, createAsyncThunk, type PayloadAction } from '@reduxjs/toolkit';
import { api } from '../../../Config/Api';
import type { Seller } from '../../../types/sellerTypes';
import axios from 'axios';

interface SellerAuthState {
    otpSent: boolean;
    error: string | null;
    loading: boolean;
    jwt: string | null;
    sellerCreated: string | null;
}

const initialState: SellerAuthState = {
    otpSent: false,
    error: null,
    loading: false,
    jwt: null,
    sellerCreated: "",
};

const API_URL = '/sellers';

export const sendLoginOtp = createAsyncThunk(
    'otp/sendLoginOtp',
    async (email: string, { rejectWithValue }) => {
        try {
            const { data } = await aapi.post('/auth/sent/login-signup-otp', { email });

            console.log('OTP sent - ', email, data);

            return { email };
        } catch (error: any) {
            console.log('OTP send error:', error);

            return rejectWithValue(
                error.response?.data?.message || 'Failed to send OTP'
            );
        }
    }
);

export const verifyLoginOtp = createAsyncThunk(
    'otp/verifyLoginOtp',
    async (
        data: {
            email: string;
            otp: string;
            navigate: any;
        },
        { rejectWithValue }
    ) => {
        try {
            const response = await api.post(
                '/sellers/verify/login-top',
                data
            );

            console.log('Seller login success - ', response.data);

            localStorage.setItem('jwt', response.data.jwt);

            data.navigate('/seller');

            return response.data;
        } catch (error: any) {
            console.log(
                'OTP verification error:',
                error.response?.data
            );

            return rejectWithValue(
                error.response?.data?.message ||
                'Failed to verify OTP'
            );
        }
    }
);

export const createSeller = createAsyncThunk<Seller, Seller>(
    'sellers/createSeller',
    async (seller: Seller, { rejectWithValue }) => {
        try {
            const response = await api.post<Seller>(
                API_URL,
                seller
            );

            console.log('Create seller:', response.data);

            return response.data;
        } catch (error: any) {
            if (axios.isAxiosError(error) && error.response) {
                console.error(
                    'Create seller error response data:',
                    error.response.data
                );

                console.error(
                    'Create seller error response status:',
                    error.response.status
                );

                console.error(
                    'Create seller error response headers:',
                    error.response.headers
                );

                return rejectWithValue(
                    error.response.data?.message ||
                    error.message
                );
            }

            console.error(
                'Create seller error message:',
                error.message
            );

            return rejectWithValue(
                'Failed to create seller'
            );
        }
    }
);

const sellerAuthSlice = createSlice({
    name: 'sellerAuth',

    initialState,

    reducers: {
        resetSellerAuthState: (state) => {
            state.otpSent = false;
            state.error = null;
            state.loading = false;
            state.jwt = null;
            state.sellerCreated = '';
        },
    },

    extraReducers: (builder) => {

        // SEND OTP
        builder
            .addCase(sendLoginOtp.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(sendLoginOtp.fulfilled, (state) => {
                state.loading = false;
                state.otpSent = true;
                state.error = null;
            })

            .addCase(sendLoginOtp.rejected, (state, action) => {
                state.loading = false;
                state.error =
                    action.payload as string ||
                    'Failed to send OTP';
            });

        // VERIFY OTP
        builder
            .addCase(verifyLoginOtp.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(verifyLoginOtp.fulfilled, (state, action) => {
                state.loading = false;
                state.jwt = action.payload.jwt;
                state.error = null;
            })

            .addCase(verifyLoginOtp.rejected, (state, action) => {
                state.loading = false;
                state.error =
                    action.payload as string ||
                    'Failed to verify OTP';
            });

        // CREATE SELLER
        builder
            .addCase(createSeller.pending, (state) => {
                state.loading = true;
                state.error = null;
            })

            .addCase(
                createSeller.fulfilled,
                (state, action: PayloadAction<Seller>) => {
                    state.sellerCreated =
                        'verification email sent to you';

                    state.loading = false;
                    state.error = null;
                }
            )

            .addCase(createSeller.rejected, (state, action) => {
                state.loading = false;
                state.error =
                    (action.payload as string) ||
                    'Failed to create seller';
            });
    },
});

export const {
    resetSellerAuthState,
} = sellerAuthSlice.actions;

export default sellerAuthSlice.reducer;