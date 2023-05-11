module Avm_Rehberi_DATA {
	exports DATA.Avm_Data;

	requires com.google.api.apicommon;
	requires com.google.auth;
	requires com.google.auth.oauth2;
	requires firebase.admin;
	requires google.cloud.core;
	requires google.cloud.firestore;
	requires json.simple;
}